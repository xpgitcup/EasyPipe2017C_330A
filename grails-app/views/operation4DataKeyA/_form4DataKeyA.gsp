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
                <f:field property="isEnumeration" id="isEnumeration" label="枚举？"/>
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
