<f:with bean="dataKeyA">
    <f:field property="dataTag" label="标签"/>
    <f:field property="dataUnit" label="单位"/>
    <f:field property="appendParameter" label="附加信息"/>
    <f:field property="dataKeyType" label="数据类型"/>
    <f:field property="columnNumber" label="列数"/>
    <f:field property="columnSeperator" label="列分隔符"/>
    <f:field property="upDataKey" label="超类"/>
     <hr>
    <f:field property="orderNumber" label="序号"/>
    <f:field property="dictionary"/>
</f:with>

<g:javascript>

    console.info('开始编辑...');

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