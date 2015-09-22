<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="1.0" encoding="utf-8" standalone="yes"/>
    <xsl:template match="/ClinicalDocument">
        <html>
            <body>
                <table width="700px" border="0">
                    <tr>
                        <td>
                            <strong>姓名：</strong>
                                <xsl:for-each select="header/patient/slot">
                                    <xsl:if test="@code='DE02.01.039.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                        </td>
                        <td>
                            <div align="right">
                                <strong>编号：</strong>
                                    <xsl:for-each select="header/event/slot">
                                        <xsl:if test="@code='EX00.00.000.54'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                </table>
                <table width="700px" class="jgridDataTable" style="">
                    <tr>
                        <td colspan="2">
                            <div align="center"><strong>随访日期</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@name='本次随访日期'">
                                        <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div align="center"><strong>随访方式</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@code='DE06.00.108.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="symptom">
                        <td colspan="2">
                            <div align="center">
                                <strong>症状</strong>
                            </div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/SYMPTOM/slot">
                                    <xsl:if test="@code='DE04.01.116.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="sign">
                        <td rowspan="4">
                            <div align="center">
                                <strong>体征</strong>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>血压(mmHg)</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/CLINICAL_SIGNS/slot">
                                    <xsl:if test="@code='DE04.10.174.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                    <xsl:if test="@code='DE04.10.176.00'">
                                        /<xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>体重(kg)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/CLINICAL_SIGNS/slot">
                                    <xsl:if test="@code='DE04.10.188.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>目标体重(kg)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/CLINICAL_SIGNS/slot">
                                    <xsl:if test="@code='DE06.00.081.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>身高(cm)</strong></div>
                        </td>
                        <td>
                            <div align="center">
                                <xsl:for-each select="structuredBody/CLINICAL_SIGNS/slot">
                                    <xsl:if test="@code='DE04.10.167.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>体质指数</strong></div>
                        </td>
                        <td>
                            <div align="center">
                                <xsl:for-each select="structuredBody/CLINICAL_SIGNS/slot">
                                    <xsl:if test="@name='体质指数'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>目标体质指数</strong></div>
                        </td>
                        <td>
                            <div align="center">
                                <xsl:for-each select="structuredBody/CLINICAL_SIGNS/slot">
                                    <xsl:if test="@name='目标体质指数'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>心率(次/min)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/CLINICAL_SIGNS/slot">
                                    <xsl:if test="@code='DE04.10.206.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>其他阳性体征</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/CLINICAL_SIGNS/slot">
                                    <xsl:if test="@code='DE04.10.143.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="lifeStyle">
                        <td rowspan="7">
                            <div align="center">
                                <strong>生活方式指导</strong>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>日吸烟量(支)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="@name='日吸烟量(支)'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>目标日吸烟量(支)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="contains(@name,'目标日吸烟量')">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>日饮酒量(两)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="@name='日饮酒量(两)'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>目标日饮酒量(两)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="contains(@name,'目标日饮酒量')">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>运动频率</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="@name='运动频率代码'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>目标运动频率</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="@name='目标运动频率代码'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>运动时长(min)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="@name='运动时长(min)'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>目标运动时长(min)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="contains(@name,'目标运动时长')">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>摄盐情况(咸淡)</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="@code='DE03.00.056.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>目标摄盐量分级</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="@code='DE03.00.046.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>心理调整</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="@code='DE05.10.083.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>随访遵医行为评价结果</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/LIFESTYLE_GUIDE/slot">
                                    <xsl:if test="@code='DE05.10.068.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="accExam">
                        <td rowspan="2">
                            <div align="center">
                                <strong>辅助检查</strong>
                            </div>
                        </td>
                        <td height="24">
                            <div align="center"><strong>检查项目</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/AUXILIARY_EXAMINATION/slot">
                                    <xsl:if test="@code='DE04.30.010.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>检查结果</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/AUXILIARY_EXAMINATION/slot">
                                    <xsl:if test="@code='DE04.30.009.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td height="33">
                            <div align="center"><strong>检查人员</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/AUXILIARY_EXAMINATION/slot">
                                    <xsl:if test="@code='EX00.00.000.07'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>检查日期</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/AUXILIARY_EXAMINATION/slot">
                                    <xsl:if test="@code='DE06.00.048.00'">
                                        <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="compliance">
                        <td colspan="2">
                            <div align="center">
                                <strong>服药依从性</strong>
                            </div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@code='DE06.00.027.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="untoward">
                        <td colspan="2">
                            <div align="center">
                                <strong>药物不良反应</strong>
                            </div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@code='DE06.00.130.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="classify">
                        <td colspan="2">
                            <div align="center">
                                <strong>此次随访分类</strong>
                            </div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@code='DE05.10.066.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="medication">
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/MEDICATION)"/>
                            </xsl:attribute>
                            <div align="center"><strong>用药情况</strong></div>
                        </td>
                        <xsl:for-each select="structuredBody/MEDICATION">
                            <xsl:if test="position()=1">
                                <td>
                                    <div align="center"><strong>药品名称</strong></div>
                                </td>
                                <td>
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE08.50.022.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                                <td>
                                    <div align="center"><strong>用法</strong></div>
                                </td>
                                <td>
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE06.00.133.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                                <td>
                                    <div align="center"><strong>用量</strong></div>
                                </td>
                                <td>
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE08.50.023.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                            <xsl:if test="@code='DE08.50.024.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                            </xsl:if>
                        </xsl:for-each>
                    </tr>
                    <xsl:for-each select="structuredBody/MEDICATION">
                        <xsl:if test="position()>1">
                            <tr>
                                <td>
                                    <div align="center"><strong>药品名称</strong></div>
                                </td>
                                <td>
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE08.50.022.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                                <td>
                                    <div align="center"><strong>用法</strong></div>
                                </td>
                                <td>
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE06.00.133.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                                <td>
                                    <div align="center"><strong>用量</strong></div>
                                </td>
                                <td>
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE08.50.023.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                            <xsl:if test="@code='DE08.50.024.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                    <tr id="transfer">
                        <td rowspan="2">
                            <div align="center">
                                <strong>转诊</strong>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>原因</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/REFERRAL/slot">
                                    <xsl:if test="@code='DE06.00.177.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>转入机构</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/REFERRAL/slot">
                                    <xsl:if test="@code='EX00.00.000.06'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>转入科室</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/REFERRAL/slot">
                                    <xsl:if test="@code='DE08.10.025.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="nextDt">
                        <td colspan="2">
                            <div align="center">
                                <strong>下次随访日期</strong>
                            </div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@name='下次随访日期'">
                                        <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div align="center">
                                <strong>随访医生</strong>
                            </div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@name='随访医生编码'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>