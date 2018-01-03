package cn.jin.web.controller.test;

import cn.jin.web.controller.test.entity.MovieResourcePO;
import cn.jin.utils.FileUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type Test
 * @Desc
 * @Date 2017-09-17 21:26
 */
@RestController
public class MovieResourceController {

    private static final Logger log = LoggerFactory.getLogger(MovieResourceController.class);

    @Resource
    private RestTemplate restTemplate;

    private static final String FILE_PATH = "E:\\workspce_jin\\ghost\\ghost-web\\src\\main\\resources\\test_1.dpl";

    private static final String END_POINT_FILE_PATH = "E:\\workspce_jin\\ghost\\ghost-web\\src\\main\\resources\\movieResource";
    private static final String END_POINT_FILE_SURFIX = ".dpl";

    private static boolean EXECUTE = false;

    @GetMapping(value = "/testUrl")
    public String getResult() {
        try {
            if (!EXECUTE) {
                ExecutorService pool = Executors.newFixedThreadPool(10);
                List<MovieResourcePO> urlList = initialUrlFile();
                int middle = (int) Math.ceil(Double.valueOf(urlList.size()) / 10D);
                log.error("url count:{};middle:{}", urlList.size(), middle);
                List<MovieResourcePO> list = null;
                for (int i = 0; i < 10; i++) {
                    if (i == 0) {
                        list = urlList.subList(0, middle);
                    } else if (i == 9) {
                        log.error("trhead:{};list start:{},end:{}", i, i * middle, urlList.size());
                        list = urlList.subList(i * middle, urlList.size());
                    } else {
                        log.error("trhead:{};list start:{},end:{}", i, i * middle, (i + 1) * middle);
                        list = urlList.subList(i * middle, (i + 1) * middle);
                    }
                    pool.execute(new MovieResourceThread(list, i));
                }
                log.error("thread submit done!");
            } else {
                log.error("方法正在执行...");
            }
            EXECUTE = true;
        } catch (Exception e) {
            log.error("execute throw exception:{}", e);
        }
        return "execute commit success";
    }

    private List<MovieResourcePO> initialUrlFile() {
        List<MovieResourcePO> list = Lists.newArrayList();
        BufferedReader reader = null;
        try {
            File file = new File(FILE_PATH);
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            MovieResourcePO po = null;
            while ((tempString = reader.readLine()) != null) {
                int index = tempString.indexOf("*file*");
                if (index != -1) {
                    line = 1;
                    po = new MovieResourcePO();
                    String realUrl = tempString.substring(index + 6, tempString.length());
                    po.setUrl(realUrl);
                } else if (line == 1) {
                    line++;
                    po.setTitle(tempString.substring(tempString.indexOf("*title*") + 7, tempString.length()));
                } else if (line == 2) {
                    po.setPlayed("*played*0");
                    if (!list.contains(po)) {
                        list.add(po);
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    private void writeFile(Set<MovieResourcePO> resourceSet) {
        if (CollectionUtils.isEmpty(resourceSet)) {
            return;
        }
        log.error("start write file!");
        AtomicInteger counter = new AtomicInteger(0);
        resourceSet.forEach(resource -> {
            if (resource != null) {
                StringBuffer data = new StringBuffer();
                data.append(counter.incrementAndGet()).append("*file*").append(resource.getUrl());
                data.append("\r\n");
                data.append(counter.get()).append("*title*").append(resource.getTitle());
                data.append("\r\n");
                data.append(counter.get()).append(resource.getPlayed());
                data.append("\r\n");
                FileUtils.writerToFile(data.toString(), END_POINT_FILE_PATH);
            }
        });
        log.error("write file success!");
    }

    private class MovieResourceThread implements Runnable {

        private List<MovieResourcePO> resourceList;

        private int fileIndex;

        public int getFileIndex() {
            return fileIndex;
        }

        public void setFileIndex(int fileName) {
            this.fileIndex = fileName;
        }

        public List<MovieResourcePO> getResourceList() {
            return resourceList;
        }

        public void setResourceList(List<MovieResourcePO> resourceList) {
            this.resourceList = resourceList;
        }

        public MovieResourceThread(List<MovieResourcePO> resourceList, int fileIndex) {
            this.resourceList = resourceList;
            this.fileIndex = fileIndex;
        }

        public MovieResourceThread() {
        }

        @Override
        public void run() {
            AtomicInteger counter = new AtomicInteger(0);
            File file = new File(END_POINT_FILE_PATH + getFileIndex() + END_POINT_FILE_SURFIX);

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    log.error("file create failed!");
                }
            }
            resourceList.forEach(resource -> {
                try {
                    restTemplate.getForObject(resource.getUrl(), String.class);
                    StringBuffer data = new StringBuffer();
                    data.append(counter.incrementAndGet()).append("*file*").append(resource.getUrl());
                    data.append("\r\n");
                    data.append(counter.get()).append("*title*").append(resource.getTitle());
                    data.append("\r\n");
                    data.append(counter.get()).append(resource.getPlayed());
                    data.append("\r\n");
                    log.error("thread{} execute index {} connection url,title:{}", getFileIndex(), counter.get(),
                            resource.getTitle());
                    FileUtils.writerToFile(data.toString(), file.getPath());
                } catch (Exception e) {
                    if (e.getMessage().indexOf("read") != -1) {
                        log.error("read timeout");
                    }
                    log.error("exception:{}", e.getMessage());
                }
            });
            log.error("thread{} execute done!", getFileIndex());
        }
    }
}
