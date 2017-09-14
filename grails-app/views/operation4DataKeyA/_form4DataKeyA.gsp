<f:with bean="dataKeyA">
    <f:field property="dataTag" label="标签"/>
    <f:field property="refDataModel" label="引用"/>
    <f:field property="dataUnit" label="单位"/>
    <!--f:field property="dimension" id="dimension" label="维度"/-->
    <span>
        <label>纬度</label>
        <input name="dimension" id="dimension" onchange="onDimension(it)"/>
    </span>
    <f:field property="appendParameter" label="附加"/>
    <f:field property="upDataKey" label="超类"/>
    <table>
        <tr>
            <td>
                <!--f:field property="isFile" id="isFile" label="文件?"/-->
                <span>
                    <label>文件？</label>
                    <input name="isFile" id="isFile" type="checkbox" onchange="onIsFile(this)"/>
                </span>
            </td>
            <td>
                <!--f:field property="isEnumeration" id="isEnumeration" label="枚举？" onchange="onIsEnumeration(this)"/-->
                <span>
                    <label>枚举？</label>
                    <input name="isEnumeration" type="checkbox" onchange="onIsEnumeration(this)"/>
                </span>
            </td>
            <td>
                <!--f:field property="single" id="single" label="单行？"/-->
                <span>
                    <label>单行？</label>
                    <input name="single" type="checkbox" onchange="onSingle(this)"/>
                </span>
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
    function onDimension(it) {
        console.info(it);
    }

    function onSingle(it) {
        //console.info(it);
        console.info(it.value);
        if (it.checked) {
            console.info('开');
        } else {
            console.info('关');
        }
    }

    function onIsFile(it) {
        //console.info(it);
        console.info(it.value);
        if (it.checked) {
            console.info('开');
        } else {
            console.info('关');
        }
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