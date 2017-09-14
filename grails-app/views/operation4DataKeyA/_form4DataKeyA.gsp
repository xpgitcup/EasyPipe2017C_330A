<f:with bean="dataKeyA">
    <f:field property="dataTag" label="标签"/>
    <f:field property="refDataModel" label="引用"/>
    <f:field property="dataUnit" label="单位"/>
    <f:field property="dimension" id="dimension" label="维度"/>
    <f:field property="appendParameter" label="附加"/>
    <f:field property="upDataKey" label="超类"/>
    <table>
        <tr>
            <td>
                <f:field property="isFile" id="isFile" label="文件?"/>
            </td>
            <td>
                <!--f:field property="isEnumeration" id="isEnumeration" label="枚举？"/-->
                <label>枚举？</label>
                <input name="isEnumeration" type="checkbox" onchange="onIsEnumeration(this)"/>
            </td>
            <td>
                <f:field property="single" id="single" label="单行？"/>
            </td>
        </tr>
    </table>
    <hr>
    <f:field property="orderNumber" label="序号"/>
    <f:field property="dictionary"/>
</f:with>

<g:javascript>

    console.info('开始编辑...');

    //var enumeration = document.getElementById('isEnumeration');
    //enumeration.addEventListener('change', onIsEnumeration(enumeration));

    /*
    * 响应函数
    * */
    function ondimension(it) {
        console.info(it);
    }

    function onIsEnumeration(it) {
        //console.info(it);
        console.info(it.value);
        if (it.checked) {
            console.info('开');
        } else {
            console.info('关');
        }
    }

</g:javascript>