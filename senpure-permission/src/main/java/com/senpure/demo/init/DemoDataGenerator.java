package com.senpure.demo.init;

import com.senpure.base.PermissionConstant;
import com.senpure.base.model.SystemValue;
import com.senpure.base.service.SystemValueService;
import com.senpure.base.spring.SpringContextRefreshEvent;
import com.senpure.base.util.RandomUtil;
import com.senpure.base.util.TimeCalculator;
import com.senpure.demo.model.Clazz;
import com.senpure.demo.model.Notice;
import com.senpure.demo.model.Proxy;
import com.senpure.demo.model.Student;
import com.senpure.demo.service.ClazzService;
import com.senpure.demo.service.NoticeService;
import com.senpure.demo.service.ProxyService;
import com.senpure.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 罗中正 on 2017/10/20.
 */
@Component
public class DemoDataGenerator extends SpringContextRefreshEvent {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ProxyService proxyService;
    @Autowired
    private SystemValueService systemValueService;

    private String[] nicks = {"齐天大圣", "狗蛋", "华阳乔布斯",
            "上好佳", "葫芦岛吴奇隆", "白良子", "西门粗吹嘘",
            "北京赵子龙", "三国战绩", "豹子头"
    };


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {


        SystemValue systemValue = systemValueService.findByKey("demo.student.create");
        if (systemValue == null || !"true".equals(systemValue.getValue())) {
            int name = RandomUtil.random(1, 9);
            Date now = new Date(System.currentTimeMillis());
            TimeCalculator.toAddDay(now, -365 * 5);
            for (int i = 0; i < 5; i++) {
                TimeCalculator.toAddDay(now, RandomUtil.random(1, 5));
                Clazz clazz = new Clazz();
                clazz.setAge(i + 1);
                clazz.setNum(1);


                clazz.setCreateTime(now.getTime());
                clazz.setCreateDate(new Date(now.getTime()));
                clazzService.save(clazz);
                List<Student> students = new ArrayList<>(128);
                for (int j = 0; j < 86 - i; j++) {
                    TimeCalculator.toAddDay(now, RandomUtil.random(1, 5));
                    Student student = new Student();
                    student.setAge(RandomUtil.random(12, 16));
                    student.setClazzId(clazz.getId());
                    student.setCreateDate(new Date(now.getTime()));
                    student.setCreateTime(now.getTime());
                    student.setName("name[" + name + "]" + i + j);
                    student.setNick(nicks[RandomUtil.random(nicks.length)]);
                    student.setNum(j + 1);
                    student.setGood(j % 3 == 0);
                    student.setPhoneNumber("1598312" + RandomUtil.random(1234, 9999));
                    students.add(student);
                }
                studentService.save(students);
            }
            if (systemValue == null) {
                systemValue = new SystemValue();
                systemValue.setKey("demo.student.create");
                systemValue.setValue("true");
                systemValue.setDescription("标记是否创建了demo数据");
                systemValue.setType(PermissionConstant.VALUE_TYPE_SYSTEM);
                systemValueService.save(systemValue);
            } else {
                systemValue.setValue("true");
                systemValueService.update(systemValue);
            }


        }
        createNotice();
        createProxy();
    }

    private void createNotice() {
        SystemValue systemValue = systemValueService.findByKey("demo.student.notice.create");
        if (systemValue == null || !"true".equals(systemValue.getValue())) {

            Date date = new Date();
            TimeCalculator.getAddDay(date, -500);
            TimeCalculator.toDayBegin(date);
            Date use = date;
            List<Notice> notices = new ArrayList<>(512);
            for (int i = 0; i < 500; i++) {
                if (i % 100 == 0) {
                    use = TimeCalculator.getAddDay(use, 100);
                }
                Notice notice = new Notice();
                notice.setMsg("msg_" + i);
                notice.setSendDate(use);
                notices.add(notice);
            }
            noticeService.save(notices);

        }

        if (systemValue == null) {
            systemValue = new SystemValue();
            systemValue.setKey("demo.student.notice.create");
            systemValue.setValue("true");
            systemValue.setDescription("标记是否创建了demo下的notice数据");
            systemValue.setType(PermissionConstant.VALUE_TYPE_SYSTEM);
            systemValueService.save(systemValue);
        } else {
            systemValue.setValue("true");
            systemValueService.update(systemValue);
        }
    }

    private void createProxy() {
        SystemValue systemValue = systemValueService.findByKey("demo.student.proxy.create");
        if (systemValue == null || !"true".equals(systemValue.getValue())) {
            int i = 1;
            List<Proxy> proxies = new ArrayList<>();


            Date date = new Date();

            for (long j = 2; j < 10; j++) {
                Proxy proxy = new Proxy();
                proxy.setParent(1L);
                proxy.setChild(j);
                proxy.setBindDate(date);
                proxy.setBindTime(date.getTime());
                proxy.setLevel(1);
                proxies.add(proxy);
            }

            for (long j = 11; j < 13; j++) {
                Proxy proxy = new Proxy();
                proxy.setParent(j - 9);
                proxy.setChild(j);
                proxy.setBindDate(date);
                proxy.setBindTime(date.getTime());
                proxy.setLevel(1);

                Proxy proxy1 = new Proxy();
                proxy1.setBindTime(date.getTime());
                proxy1.setParent(1L);
                proxy1.setChild(j);
                proxy1.setLevel(2);
                proxy1.setBindDate(date);

                proxies.add(proxy);
                proxies.add(proxy1);
            }
            proxyService.save(proxies);
        }
        if (systemValue == null) {
            systemValue = new SystemValue();
            systemValue.setKey("demo.student.proxy.create");
            systemValue.setValue("true");
            systemValue.setDescription("标记是否创建了demo下的proxy数据");
            systemValue.setType(PermissionConstant.VALUE_TYPE_SYSTEM);
            systemValueService.save(systemValue);
        } else {
            systemValue.setValue("true");
            systemValueService.update(systemValue);
        }
    }
}
