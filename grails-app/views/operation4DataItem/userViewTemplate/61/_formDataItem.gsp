<%@ page import="cn.edu.cup.dictionary.DataItem" %>
<table>
  <tr>
    <td>
      <div class='fieldcontain' required='required'>
        <label for='labelKey'>${dataItemInstance?.labelKey}
          <span class='required-indicator'>*</span>
        </label>
        <input type='text' name='labelKey.id' value='${dataItemInstance?.labelKey?.id}' readonly='readonly' />
      </div>
    </td>
    <td>
      <div class='fieldcontain' required='required'>
        <label for='value'>value</label>
        <input type='text' name='value' value='${dataItemInstance?.value}' />
      </div>
    </td>
  </tr>
  <tr>
    <td>
      <span>站点位置数据
        <input type='hidden' name='subItems[0].labelKey.id' value='${dataItemInstance.subItems[0].labelKey.id}' />
      </span>
    </td>
    <td>
      <input type='text' name='subItems[0].value' value='${dataItemInstance.subItems[0].value}' />
    </td>
    <td>
      <input type='hidden' name='subItems[0].parentItem.id' value='${dataItemInstance.id}' />
    </td>
  </tr>
  <tr>
    <td>
      <span>管线名称
        <input type='hidden' name='subItems[1].labelKey.id' value='${dataItemInstance.subItems[1].labelKey.id}' />
      </span>
    </td>
    <td>
      <input type='text' name='subItems[1].value' value='${dataItemInstance.subItems[1].value}' />
    </td>
    <td>
      <input type='hidden' name='subItems[1].parentItem.id' value='${dataItemInstance.id}' />
    </td>
  </tr>
</table>