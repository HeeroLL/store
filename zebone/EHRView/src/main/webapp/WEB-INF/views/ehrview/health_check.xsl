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
                        <td colspan="2"><div align="center"><strong>体检日期</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION_INFOMATION/slot">
                                <xsl:if test="@code='DE06.00.048.00'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>责任医生</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION_INFOMATION/slot">
                                <xsl:if test="@name='责任医生姓名'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="symptom">
                        <td><div align="center"><strong>症状</strong></div></td>
                        <td colspan="6"><div>
                            <xsl:for-each select="structuredBody/SYMPTOM/slot">
                                <xsl:if test="@code='DE04.01.116.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="general">
                        <td width="20" rowspan="5"><div align="center"><strong>一般体征</strong></div></td>
                        <td><div align="center"><strong>体温(℃)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.186.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="104"><div align="center"><strong>脉搏(次/min)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.118.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="2"><div align="center"><strong>呼吸频率(次/min)</strong></div></td>
                        <td colspan="2" rowspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.082.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td rowspan="2"><div align="center"><strong>血压</strong></div></td>
                        <td><div align="center"><strong>左侧(mmHg)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="contains(@name,'左侧收缩压')">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                                <xsl:if test="contains(@name,'左侧舒张压')">
                                    /<xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>右侧(mmHg)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="contains(@name,'右侧收缩压')">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="contains(@name,'右侧舒张压')">
                                    /<xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>身高(cm)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.167.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>体重(kg)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.10.188.00'">
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
                        <td><div align="center"><strong>体质指数</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE05.10.075.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="older">
                        <td rowspan="4"><div align="center"><strong>老年人</strong></div></td>
                        <td><div align="center"><strong>健康状态自我评估</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE04.01.036.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>生活自理能力自我评估</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE05.10.043.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>认知功能</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE05.10.041.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>情感状态</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/GENERAL_CONDITION/slot">
                                <xsl:if test="@code='DE05.10.040.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="lifeStyle">
                        <td rowspan="14"><div align="center"><strong>生活方式</strong></div></td>
                        <td rowspan="3"><div align="center"><strong>体育锻炼</strong></div></td>
                        <td><div align="center"><strong>锻炼频率</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.087.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>每次锻炼时间(min)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.088.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>坚持锻炼时间(月)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.024.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>锻炼方式</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.086.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>饮食习惯</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.081.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="3"><div align="center"><strong>吸烟情况</strong></div></td>
                        <td><div align="center"><strong>吸烟状况</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.073.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>日吸烟量(支)</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.053.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>开始吸烟年龄(岁)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.036.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>戒烟年龄(岁)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.032.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="5"><div align="center"><strong>饮酒情况</strong></div></td>
                        <td><div align="center"><strong>饮酒频率</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.076.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>日饮酒量(两)</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.054.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>是否戒酒</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.030.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>开始饮酒年龄(岁)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.037.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>近一年内是否曾醉酒</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.098.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>饮酒种类</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/DRINKING_SPECIES/slot">
                                <xsl:if test="position()>1">，</xsl:if>
                                <xsl:if test="@code='DE03.00.078.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="2"><div align="center"><strong>职业病危害因素接触史</strong></div></td>
                        <td><div align="center"><strong>职业暴露标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.089.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>工种</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.083.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>从业时间</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/slot">
                                <xsl:if test="@code='DE03.00.007.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>毒物种类</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/LIFE_STYLE/OCCUPATIONAL_EXPOSURE_RISK_FACTORS">
                                <xsl:if test="position()>1">，</xsl:if>
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE03.00.091.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="organ">
                        <td rowspan="7"><div align="center"><strong>脏器功能</strong></div></td>
                        <td rowspan="3"><div align="center"><strong>口腔</strong></div></td>
                        <td><div align="center"><strong>口唇</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="@code='DE04.10.106.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>齿列</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="@code='DE04.10.016.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>齿列描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="@code='DE04.10.017.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>咽部</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="@code='DE04.10.214.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="2"><div align="center"><strong>视力</strong></div></td>
                        <td><div align="center"><strong>左眼裸眼远视力值</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="contains(@name,'左眼裸眼')">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>右眼裸眼远视力值</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="contains(@name,'右眼裸眼')">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>左眼矫正远视力值</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="contains(@name,'左眼矫正')">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>右眼矫正远视力值</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="contains(@name,'右眼矫正')">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>听力</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="@code='DE04.10.190.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>运动功能</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/ORGAN_FUNCTION/slot">
                                <xsl:if test="@code='DE05.10.106.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="examination">
                        <td rowspan="24"><div align="center"><strong>查体</strong></div></td>
                        <td><div align="center"><strong>眼底</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.215.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>皮肤</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.129.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>巩膜</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.075.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>淋巴结</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.113.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="3"><div align="center"><strong>肺</strong></div></td>
                        <td><div align="center"><strong>桶状胸标志</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.191.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>肺部异常呼吸音标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@name='肺部异常呼吸音标志'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>肺部异常呼吸音描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@name='肺部异常呼吸音描述'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>肺部罗音标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@name='肺部罗音标志'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>肺部罗音描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@name='肺部罗音描述'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="2"><div align="center"><strong>心脏</strong></div></td>
                        <td><div align="center"><strong>心率(次/min)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.206.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>心律</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.205.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>心脏杂音标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.209.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>心脏杂音描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.210.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="5"><div align="center"><strong>腹部</strong></div></td>
                        <td><div align="center"><strong>腹部压痛标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.048.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>腹部压痛描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.049.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>腹部包块标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.044.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>腹部包块描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.045.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>肝大标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.054.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>肝大描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.055.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>脾大标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.138.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>脾大描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.139.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>腹部移动性浊音标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.050.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>腹部移动性浊音描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.051.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>下肢水肿</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.201.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>足背动脉搏动</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.238.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>肛门指诊</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.064.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>乳腺</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.159.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="5"><div align="center"><strong>妇科</strong></div></td>
                        <td><div align="center"><strong>外阴异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.198.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>外阴异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='EX00.00.001.06'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>阴道异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.223.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>阴道异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.224.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>宫颈异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.070.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>宫颈异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.071.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>宫体异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.072.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>宫体异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.073.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>附件异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.041.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>附件异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.042.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>其他查体结果</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION/slot">
                                <xsl:if test="@code='DE04.10.142.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="auxiliary">
                        <td rowspan="25"><div align="center"><strong>辅助检查</strong></div></td>
                        <td rowspan="2"><div align="center"><strong>血常规</strong></div></td>
                        <td><div align="center"><strong>血红蛋白值(g/L)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.091.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>白细胞计数值(G/L)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.015.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>血小板计数值(G/L)</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.015.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="5"><div align="center"><strong>尿常规</strong></div></td>
                        <td><div align="center"><strong>尿蛋白定性检测结果</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.050.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>尿蛋白定量检测值(mg/24h)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.049.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>尿糖定性检测结果</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.062.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>尿糖定量检查(mmol/L)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.061.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>尿酮体定性检测结果</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.063.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>尿潜血检测结果</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.057.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>尿比重</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.046.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>尿液酸碱度</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.066.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>尿微量白蛋白(mg/dL)</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.064.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>血糖</strong></div></td>
                        <td><div align="center"><strong>空腹血糖值(mmol/L)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.037.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>餐后2小时血糖值(mmol/L)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.019.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>心电图</strong></div></td>
                        <td><div align="center"><strong>心电图异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE05.10.081.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>心电图异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE05.10.082.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>大便潜血标志</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.022.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>糖化血红蛋白(%)</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.083.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="2"><div align="center"><strong>乙型肝炎</strong></div></td>
                        <td><div align="center"><strong>乙肝病毒表面抗原检查结果</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.116.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>乙肝病毒e抗原检测结果</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.114.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>乙肝病毒表面抗体检测结果</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.115.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="3"><div align="center"><strong>肝功能</strong></div></td>
                        <td><div align="center"><strong>血清谷丙转氨酶值(U/L)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.099.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>血清谷草转氨酶值(U/L)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.127.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>白蛋白浓度(g/L)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.013.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>总胆红素值(μmol/L)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.126.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>结合胆红素值(μmol/L)</strong></div></td>
                        <td colspan="4"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.034.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="2"><div align="center"><strong>肾功能</strong></div></td>
                        <td><div align="center"><strong>血肌酐值(μmol/L)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.092.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>血尿素氮检测值(mmol/L)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.095.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>血钾浓度(mmol/L)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.093.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>血钠浓度(mmol/L)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.094.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td rowspan="3"><div align="center"><strong>血脂</strong></div></td>
                        <td><div align="center"><strong>总胆固醇(mmol/L)</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.125.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>甘油三酯值(mmol/L)</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.025.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>血清低密度脂蛋白胆固醇(mmol/L)</strong></div></td>
                        <td colspan="3"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.097.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>血清高密度脂蛋白胆固醇(mmol/L)</strong></div></td>
                        <td colspan="3"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.098.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>胸部X线</strong></div></td>
                        <td><div align="center"><strong>胸部X线检查异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.30.046.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>胸部X线检查异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.30.045.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>B超</strong></div></td>
                        <td><div align="center"><strong>B超检查异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.30.003.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>B超检查异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.30.002.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>宫颈涂片</strong></div></td>
                        <td><div align="center"><strong>宫颈涂片异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.029.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>宫颈涂片异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.50.030.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>其他辅助检查</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/AUXILIMARY_EXAMINATION/slot">
                                <xsl:if test="@code='DE04.30.030.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="constitution">
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/TCM_CONSITITUTION_IDENTIFICATION)"/>
                            </xsl:attribute>
                            <div align="center">
                                <strong>中医体质辨识</strong>
                            </div>
                        </td>
                        <xsl:for-each select="structuredBody/TCM_CONSITITUTION_IDENTIFICATION">
                            <xsl:if test="position()=1">
                                <td>
                                    <div align="center"><strong>中医体质分类</strong></div>
                                </td>
                                <td colspan="2">
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE05.01.065.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                                <td>
                                    <div align="center"><strong>中医体质分类判定结果</strong></div>
                                </td>
                                <td colspan="2">
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE05.01.073.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                            </xsl:if>
                        </xsl:for-each>
                    </tr>
                    <xsl:for-each select="structuredBody/TCM_CONSITITUTION_IDENTIFICATION">
                        <xsl:if test="position()>1">
                            <tr>
                                <td>
                                    <div align="center"><strong>中医体质分类</strong></div>
                                </td>
                                <td colspan="2">
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE05.01.065.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                                <td>
                                    <div align="center"><strong>中医体质分类判定结果</strong></div>
                                </td>
                                <td colspan="2">
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE05.01.073.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                    <tr id="healthPro">
                        <td><div align="center"><strong>现存主要健康问题</strong></div></td>
                        <td colspan="6"><div align="center">
                            <xsl:for-each select="structuredBody/EXISTING_MAJOR_HEALTH_PROBLEMS/slot">
                                <xsl:if test="position()>1">，</xsl:if>
                                <xsl:if test="@code='DE05.10.080.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="hospitalization">
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/HOSPITALIZATION_TREATMENT/HOSPITALIZATION_HISTORY)+1+
                                count(structuredBody/HOSPITALIZATION_TREATMENT/FAMILY_BED_HISTORY)+1"/>
                            </xsl:attribute>
                            <div align="center"><strong>住院治疗情况</strong></div></td>
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/HOSPITALIZATION_TREATMENT/HOSPITALIZATION_HISTORY)+1"/>
                            </xsl:attribute>
                            <div align="center"><strong>住院史</strong></div></td>
                        <td><div align="center"><strong>入院时间</strong></div></td>
                        <td><div align="center"><strong>出院时间</strong></div></td>
                        <td><div align="center"><strong>原因</strong></div></td>
                        <td><div align="center"><strong>医疗机构名称</strong></div></td>
                        <td><div align="center"><strong>病案号</strong></div></td>
                    </tr>
                    <xsl:for-each select="structuredBody/HOSPITALIZATION_TREATMENT/HOSPITALIZATION_HISTORY">
                    <tr>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE06.00.091.00'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE06.00.016.00'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE05.10.053.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE08.10.013.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE01.00.004.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    </xsl:for-each>
                    <tr>
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/HOSPITALIZATION_TREATMENT/FAMILY_BED_HISTORY)+1"/>
                            </xsl:attribute>
                            <div align="center"><strong>家庭病床史</strong></div></td>
                        <td><div align="center"><strong>建床时间</strong></div></td>
                        <td><div align="center"><strong>撤床时间</strong></div></td>
                        <td><div align="center"><strong>原因</strong></div></td>
                        <td><div align="center"><strong>医疗机构名称</strong></div></td>
                        <td><div align="center"><strong>病案号</strong></div></td>
                    </tr>
                    <xsl:for-each select="structuredBody/HOSPITALIZATION_TREATMENT/FAMILY_BED_HISTORY">
                    <tr>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE06.00.046.00'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE06.00.045.00'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE06.00.047.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE08.10.013.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center">
                            <xsl:for-each select="slot">
                                <xsl:if test="@code='DE01.00.004.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    </xsl:for-each>
                    <tr id="medication">
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/MAIN_MEDICATION)+1"/>
                            </xsl:attribute>
                            <div align="center"><strong>用药情况</strong></div></td>
                        <td><div align="center"><strong>中药类别</strong></div></td>
                        <td><div align="center"><strong>药物名称</strong></div></td>
                        <td><div align="center"><strong>用法</strong></div></td>
                        <td><div align="center"><strong>用量</strong></div></td>
                        <td><div align="center"><strong>药物使用途径</strong></div></td>
                        <td><div align="center"><strong>服药依从性</strong></div></td>
                    </tr>
                    <xsl:for-each select="structuredBody/MAIN_MEDICATION">
                        <tr>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE06.00.164.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE08.50.022.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE06.00.133.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE08.50.023.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE06.00.134.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE06.00.027.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                        </tr>
                    </xsl:for-each>
                    <tr id="vaccination">
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/NON_IMMUNIZATION_PROGRAM_VACCINATION_HISTORY)+1"/>
                            </xsl:attribute>
                            <div align="center"><strong>非免疫规划预防接种史</strong></div></td>
                        <td colspan="2"><div align="center"><strong>名称</strong></div></td>
                        <td><div align="center"><strong>接种日期</strong></div></td>
                        <td colspan="3"><div align="center"><strong>接种机构</strong></div></td>
                    </tr>
                    <xsl:for-each select="structuredBody/NON_IMMUNIZATION_PROGRAM_VACCINATION_HISTORY">
                        <tr>
                            <td colspan="2"><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE08.50.016.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE06.00.145.00'">
                                        <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td colspan="3"><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE08.50.015.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                        </tr>
                    </xsl:for-each>
                    <tr id="assess">
                        <td><div align="center"><strong>健康评估</strong></div></td>
                        <td colspan="2"><div align="center"><strong>健康评价异常标志</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION_INFOMATION/slot">
                                <xsl:if test="@code='DE05.10.031.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>健康评价异常描述</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/EXAMINATION_INFOMATION/slot">
                                <xsl:if test="@code='DE05.10.032.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="control">
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/EXAMINATION_INFOMATION/RISK_FACTOR_CONTROLS)+1"/>
                            </xsl:attribute>
                            <div align="center"><strong>危险因素控制</strong></div></td>
                        <td colspan="2"><div align="center"><strong>危险因素控制建议</strong></div></td>
                        <td colspan="4"><div align="center"><strong>建议详情</strong></div></td>
                    </tr>
                    <xsl:for-each select="structuredBody/EXAMINATION_INFOMATION/RISK_FACTOR_CONTROLS">
                        <tr>
                            <td colspan="2"><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE06.00.114.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td colspan="4"><div align="center"></div></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>