<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="1.0" encoding="utf-8" standalone="yes"/>
    <xsl:template match="/ClinicalDocument">
        <html>
            <body>
                <table width="700px" border="0">
                    <tr>
                        <td>
                            <strong>卡片编号：</strong>
                            <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                <xsl:if test="@code='EX00.00.000.05'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </td>
                        <td>
                            <div align="right">
                                <strong>报卡类型：</strong>
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@code='DE01.00.002.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                </table>
                <table width="700px" class="jgridDataTable" style="">
                    <tr>
                        <td width="20" rowspan="2"><div align="center"><strong>基本信息</strong></div></td>
                        <td width="103" height="16"><div align="center"><strong>服务机构</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                <xsl:if test="@code='EX00.00.000.06'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="117"><div align="center"><strong>报卡医生</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                <xsl:if test="@code='EX00.00.000.07'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>建卡日期</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE09.00.031.00'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="5"><div align="center"><strong>人员信息</strong></div></td>
                        <td><div align="center"><strong>姓名</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.039.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>身份证号</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.030.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>出生日期</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.005.01'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>工作单位</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_INFORMATION/slot">
                                <xsl:if test="@code='DE08.10.007.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>本人电话</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.010.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>居住地址</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_INFORMATION/slot">
                                <xsl:if test="@code='EX00.00.000.04'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>职业</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.052.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="6"><div align="center"><strong>糖尿病详细情况</strong></div></td>
                        <td><div align="center"><strong>发现日期</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.000.31'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>发现方式</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.000.30'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>确诊日期</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='DE05.01.034.00'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>确诊机构</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.000.06'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>空腹血糖</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='DE04.50.037.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>餐后2小时血糖</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='DE04.50.019.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>糖化血红蛋白</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='DE04.50.083.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>知晓情况</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.000.32'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>糖尿病类型</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.000.37'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>糖尿病管理级别</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.000.38'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>社区监管标志</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/DIABETES_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.000.36'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>并发症情况</strong></div></td>
                        <td colspan="5">
                            <xsl:for-each select="structuredBody/COMPLICATIONS/slot">
                                <xsl:if test="@code='DE04.01.116.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>