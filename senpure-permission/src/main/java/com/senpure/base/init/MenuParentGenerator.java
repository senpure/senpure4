package com.senpure.base.init;


import com.senpure.base.menu.MenuGenerator;
import com.senpure.base.model.Menu;
import com.senpure.base.spring.SpringContextRefreshEvent;
import com.senpure.base.util.Pinyin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by 罗中正 on 2017/6/15.
 */
@Component
@Order(0)
public class MenuParentGenerator extends SpringContextRefreshEvent {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String beans[] = context.getBeanNamesForType(Object.class);
        for (String bean : beans) {
            Object o = context.getBean(bean);
            MenuGenerator menuGenerator = o.getClass().getAnnotation(MenuGenerator.class);
            if (menuGenerator != null) {
                Menu menu = new Menu();
                menu.setDatabaseUpdate(false);
                menu.setDirectView(false);
                menu.setText(menuGenerator.text());

                if (menuGenerator.i18nKey().length() > 0) {
                    menu.setI18nKey(menuGenerator.i18nKey());
                } else {
                    menu.setI18nKey(Pinyin.toAccount(menu.getText())[0].replace(" ",".").toLowerCase());
                }
                if (menuGenerator.description().length() > 0) {
                    menu.setDescription(menuGenerator.description());
                }
                if (menuGenerator.config().length() > 0) {
                    menu.setConfig(menuGenerator.config());
                }
                menu.setIcon(menuGenerator.icon());
                menu.setId(menuGenerator.id());
                menu.setSort(menuGenerator.sort());
                PermissionsGenerator.checkAndPutMenu(menu);
            }

        }
    }
}
