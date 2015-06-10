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
                    <tr id="baseInfo" >
                        <td width="19" rowspan="2">
                            <div align="center">
                                <strong>基本信息</strong>
                            </div>
                        </td>
                        <td width="100">
                            <div align="center"><strong>服务机构</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <div align="center">
                                    <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                        <xsl:if test="@name='服务机构代码'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div>
                            </div>
                        </td>
                        <td width="95">
                            <div align="center"><strong>报卡医生</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@name='报卡医师编码'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>建卡日期</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/BASIC_INFORMATION/slot">
                                    <xsl:if test="@name='建卡日期'">
                                        <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="patientInfo">
                        <td rowspan="5">
                            <div align="center">
                                <strong>患者信息</strong>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>姓名</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/PATIENT_INFORMATION/slot">
                                    <xsl:if test="@code='DE02.01.039.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>身份证号</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/PATIENT_INFORMATION/slot">
                                    <xsl:if test="@name='身份证件号码'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>出生日期</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/PATIENT_INFORMATION/slot">
                                    <xsl:if test="@name='出生日期'">
                                        <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr >
                        <td>
                            <div align="center"><strong>工作单位</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/PATIENT_INFORMATION/slot">
                                    <xsl:if test="@name='工作单位'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>本人电话</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/PATIENT_INFORMATION/slot">
                                    <xsl:if test="@name='本人电话'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>居住地址</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/PATIENT_INFORMATION/slot">
                                    <xsl:if test="@name='居住地址-详细地址'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>职业</strong></div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/PATIENT_INFORMATION/slot">
                                    <xsl:if test="@name='职业'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="detailInfo" >
                        <td rowspan="5">
                            <div align="center">
                                <strong>高血压详细情况</strong>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>发现日期</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@code='EX00.00.000.31'">
                                        <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>发现方式</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@code='EX00.00.000.30'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr >
                        <td>
                            <div align="center"><strong>确诊日期</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@name='确诊日期'">
                                        <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>确诊机构</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@name='确诊机构编码'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr >
                        <td>
                            <div align="center"><strong>血压</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@code='DE04.10.174.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                    <xsl:if test="@code='DE04.10.176.00'">
                                        /<xsl:value-of select="value/@displayName"/>mmHg
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>知晓情况</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@name='知晓情况代码'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr >
                        <td>
                            <div align="center"><strong>高血压类型</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@code='EX00.00.000.33'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>高血压水平分级</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@code='EX00.00.000.34'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr >
                        <td>
                            <div align="center"><strong>高血压管理级别</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@code='EX00.00.000.35'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>社区监管标志</strong></div>
                        </td>
                        <td colspan="2">
                            <div align="center">
                                <xsl:for-each select="structuredBody/HYPERTENSION_DETAILS/slot">
                                    <xsl:if test="@code='EX00.00.000.36'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr id="complication" >
                        <td colspan="2">
                            <div align="center">
                                <strong>并发症情况</strong>
                            </div>
                        </td>
                        <td colspan="5">
                            <div align="center">
                                <xsl:for-each select="structuredBody/COMPLICATIONSs/slot">
                                    <xsl:if test="@code='DE04.01.116.00'">
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