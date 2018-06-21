package ${package};

<#if package !="com.senpure.base.configuraion">
import com.senpure.base.configuration.BaseConfiguration;
</#if>
import com.senpure.base.cache.LocalRemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;

/**
 * @author senpure-generator
 * @version ${.now?datetime}
 */
@Configuration
@ConditionalOnClass({RedisTemplate.class, CacheManager.class})
public class ${configName} extends BaseConfiguration {

    private String[] localNames = {<#list names as name>"${nameRule(name)}"<#if name_has_next>, </#if></#list>};
    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        if (cacheManager instanceof LocalRemoteCacheManager) {
            LocalRemoteCacheManager localRemoteCacheManager = (LocalRemoteCacheManager) cacheManager;
            for (String name : localNames) {
                localRemoteCacheManager.addLocalName(name);
            }
        }
    }
}