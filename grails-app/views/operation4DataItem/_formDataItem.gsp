<%@ page import="cn.edu.cup.dictionary.DataItem" %>

<table>
    <tr>
        <td>
            <div class="fieldcontain ${hasErrors(bean: dataItemInstance, field: 'labelKey', 'error')} required">
                <label for="labelKey">
                    ${dataItemInstance?.labelKey}
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="labelKey.id" value="${dataItemInstance?.labelKey?.id}" readonly="readonly"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: dataItemInstance, field: 'value', 'error')} ">
                <label for="value">
                    <g:message code="dataItem.value.label" default="Value"/>
                </label>
                <g:textField name="value" value="${dataItemInstance?.value}"/>

            </div>
        </td>
    </tr>
    <g:each in="${dataItemInstance.subItems}" status="i" var="item">
        <tr>
            <td>
                <span>
                    ${item.labelKey}
                    <g:hiddenField name="subItems[${i}].labelKey.id" value="${dataItemInstance.subItems[i].labelKey.id}" />
                </span>
            </td>
            <td>
                <g:textField name="subItems[${i}].value" value="${dataItemInstance.subItems[i].value}" />
            </td>
            <td>
                <g:hiddenField name="subItems[${i}].parentItem.id" value="${dataItemInstance.id}" />
            </td>
        </tr>
    </g:each>
</table>



