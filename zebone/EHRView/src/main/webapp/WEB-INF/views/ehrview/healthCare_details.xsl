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
                        <td width="19" rowspan="2"><div align="center"><strong>基本信息</strong></div></td>
                        <td width="100" height="24"><div align="center"><strong>随访日期</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                <xsl:if test="@name='本次随访日期'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>责任医生</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                <xsl:if test="@name='责任医生编码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>症状</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/SYMPTOM/slot">
                                <xsl:if test="@code='DE04.01.116.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="8"><div align="center"><strong>体征</strong></div></td>
                        <td><div align="center"><strong>血压(mmHg)</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.174.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                                <xsl:if test="@code='DE04.10.176.00'">
                                    /<xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td width="94"><div align="center"><strong>身高(cm)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.167.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>体重(kg)</strong></div></td>
                        <td width="84"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.188.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="74"><div align="center"><strong>体质指数</strong></div></td>
                        <td width="149"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE05.10.075.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>腰围(cm)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.218.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>体型评价</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='EX00.00.000.44'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>听力情况</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.190.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>视力情况</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='EX00.00.000.45'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>牙齿残缺</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='EX00.00.000.46'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>老年人情感状态</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE05.10.040.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>老年人生活自理能力评估</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE05.10.043.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>服药依从性</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE06.00.027.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
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
                    <tr>
                        <td colspan="2"><div align="center"><strong>下次随访日期</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                <xsl:if test="@name='下次随访日期'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>随访医生</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                <xsl:if test="@name='随访医生编码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>