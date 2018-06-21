<#if total??&&items??>
    <#if total gt 0&&(items?size>0)>
    <div class="table-responsive" data-uri="query"
         data-total="${total}"
         data-form="#criteria-pagination"
         data-page-size="${criteria.pageSize}"
         data-page="${criteria.page}"
         data-form="#criteria-pagination"
         data-first-text="<@spring.message 'label.page.first'/>"
         data-last-text="<@spring.message "label.page.last"/>"
         data-page-show-text="<@spring.message "label.page.every.show"/>"
         data-total-text="<@spring.message "label.record.all"/>"
         data-page-show-data='[{"value":15,"text":15},{"value":30,"text":30},{"value":50,"text":50},{"value":80,"text":80}]'
    >
    <#else>
        <div class="text-danger margin-top-10">
            <@spring.message "label.result.empty"/>
        </div>
    </#if>
</#if>