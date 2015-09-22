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
                    <tr id="healthRec">
                        <td colspan="2"><div align="center"><strong>性别</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE02.01.040.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>出生日期</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE02.01.005.01'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>身份证号</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE02.01.030.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>工作单位</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE08.10.007.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>本人电话</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@name='本人电话号码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>联系人姓名</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@name='联系人姓名'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>联系人电话</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@name='联系人电话号码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>常住类型</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE02.01.003.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>民族</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE02.01.025.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>ABO血型</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE04.50.001.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>Rh血型</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE04.50.010.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>文化程度</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE02.01.041.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>职业</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE02.01.052.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>婚姻状况</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE02.01.018.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>医疗费用支付方式</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_HEALTH_RECORD/slot">
                                <xsl:if test="@code='DE07.00.007.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="allergy">
                        <td colspan="2"><div align="center"><strong>药物过敏史</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="structuredBody/PERSON_ALLERGY/slot">
                                <xsl:if test="position()>1">，</xsl:if>
                                <xsl:if test="@code='DE05.01.022.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="exposure">
                        <td colspan="2"><div align="center"><strong>暴露史</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="structuredBody/PERSON_EXPOSURE_HISTORY/slot">
                                <xsl:if test="position()>1">，</xsl:if>
                                <xsl:if test="@code='DE03.00.021.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="pastHis">
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/PERSON_PAST_HISTORY)+
                                count(structuredBody/PERSON_OPERATION_HISTORY)+count(structuredBody/PERSON_TRAUMATISM_HISTORY)+
                                count(structuredBody/PERSON_TRANSFUSE_HISTORY)"/>
                            </xsl:attribute>
                            <div align="center">
                                <strong>既往史</strong>
                            </div>
                        </td>
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/PERSON_PAST_HISTORY)"/>
                            </xsl:attribute>
                            <div align="center"><strong>疾病</strong></div>
                        </td>
                        <xsl:for-each select="structuredBody/PERSON_PAST_HISTORY">
                            <xsl:if test="position()=1">
                                <td>
                                    <div align="center"><strong>疾病名称</strong></div>
                                </td>
                                <td>
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE02.10.021.00'">
                                                <xsl:value-of select="value/@displayName"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                                <td>
                                    <div align="center"><strong>确诊时间</strong></div>
                                </td>
                                <td colspan="2">
                                    <div align="center">
                                        <xsl:for-each select="slot">
                                            <xsl:if test="@code='DE05.01.035.00'">
                                                <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                                <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                                <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </div>
                                </td>
                            </xsl:if>
                        </xsl:for-each>
                    </tr>
                    <xsl:for-each select="structuredBody/PERSON_PAST_HISTORY">
                        <xsl:if test="position()>1">
                            <tr>
                                <td><div align="center"><strong>疾病名称</strong></div></td>
                                <td><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.021.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                                <td><div align="center"><strong>确诊时间</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE05.01.035.00'">
                                            <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                    <tr>
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/PERSON_OPERATION_HISTORY)"/>
                            </xsl:attribute>
                            <div align="center"><strong>手术</strong></div></td>
                        <xsl:for-each select="structuredBody/PERSON_OPERATION_HISTORY">
                            <xsl:if test="position()=1">
                                <td><div align="center"><strong>手术名称</strong></div></td>
                                <td><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.061.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                                <td><div align="center"><strong>手术时间</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE06.00.095.00'">
                                            <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                            </xsl:if>
                        </xsl:for-each>
                    </tr>
                    <xsl:for-each select="structuredBody/PERSON_OPERATION_HISTORY">
                        <xsl:if test="position()>1">
                            <tr>
                            <td><div align="center"><strong>手术名称</strong></div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE02.10.061.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center"><strong>手术时间</strong></div></td>
                            <td colspan="2"><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE06.00.095.00'">
                                        <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                        <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                    <tr>
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/PERSON_TRAUMATISM_HISTORY)"/>
                            </xsl:attribute>
                            <div align="center"><strong>外伤</strong></div></td>
                        <xsl:for-each select="structuredBody/PERSON_TRAUMATISM_HISTORY">
                            <xsl:if test="position()=1">
                                <td><div align="center"><strong>外伤名称</strong></div></td>
                                <td><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.068.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                                <td><div align="center"><strong>发生时间</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.067.00'">
                                            <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                            </xsl:if>
                        </xsl:for-each>
                    </tr>
                    <xsl:for-each select="structuredBody/PERSON_TRAUMATISM_HISTORY">
                        <xsl:if test="position()>1">
                            <tr>
                                <td><div align="center"><strong>外伤名称</strong></div></td>
                                <td><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.068.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                                <td><div align="center"><strong>发生时间</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.067.00'">
                                            <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                    <tr>
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/PERSON_TRANSFUSE_HISTORY)"/>
                            </xsl:attribute>
                            <div align="center"><strong>输血</strong></div>
                        </td>
                        <xsl:for-each select="structuredBody/PERSON_TRANSFUSE_HISTORY">
                            <xsl:if test="position()=1">
                                <td><div align="center"><strong>输血原因</strong></div></td>
                                <td><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE06.00.107.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                                <td><div align="center"><strong>输血时间</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE06.00.105.00'">
                                            <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                            </xsl:if>
                        </xsl:for-each>
                    </tr>
                    <xsl:for-each select="structuredBody/PERSON_TRANSFUSE_HISTORY">
                        <xsl:if test="position()>1">
                            <tr>
                               <td><div align="center"><strong>输血原因</strong></div></td>
                               <td><div align="center">
                                   <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE06.00.107.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                                <td><div align="center"><strong>输血时间</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE06.00.105.00'">
                                            <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                            <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                    <tr id="familyHis">
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(structuredBody/PERSON_FAMILY_HISTORY)"/>
                            </xsl:attribute>
                            <div align="center"><strong>家族史</strong></div>
                        </td>
                        <xsl:for-each select="structuredBody/PERSON_FAMILY_HISTORY">
                            <xsl:if test="position()=1">
                                <td><div align="center"><strong>与本人关系</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.024.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                                <td><div align="center"><strong>疾病名称</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.095.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                            </xsl:if>
                        </xsl:for-each>
                    </tr>
                    <xsl:for-each select="structuredBody/PERSON_FAMILY_HISTORY">
                        <xsl:if test="position()>1">
                            <tr>
                                <td><div align="center"><strong>与本人关系</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.024.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                                <td><div align="center"><strong>疾病名称</strong></div></td>
                                <td colspan="2"><div align="center">
                                    <xsl:for-each select="slot">
                                        <xsl:if test="@code='DE02.10.095.00'">
                                            <xsl:value-of select="value/@displayName"/>
                                        </xsl:if>
                                    </xsl:for-each>
                                </div></td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                    <tr id="geneticHis">
                        <td colspan="2"><div align="center"><strong>遗传病史</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="structuredBody/PERSON_GENETIC_HISTORY/slot">
                                <xsl:if test="position()>1">，</xsl:if>
                                <xsl:if test="@code='DE02.10.026.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="disability">
                        <td colspan="2"><div align="center"><strong>残疾情况</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="structuredBody/PERSON_DISABILITY_HISTORY/slot">
                                <xsl:if test="position()>1">，</xsl:if>
                                <xsl:if test="@code='DE05.10.006.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr id="lifeEnv">
                        <td rowspan="5"><div align="center"><strong>生活环境</strong></div></td>
                        <td><div align="center"><strong>厨房排风设施</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_LIFE_ENVIRONMENT/slot">
                                <xsl:if test="@code='DE03.00.006.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>燃料类型</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_LIFE_ENVIRONMENT/slot">
                                <xsl:if test="@code='EX00.00.000.22'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>饮水</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_LIFE_ENVIRONMENT/slot">
                                <xsl:if test="@code='DE03.00.082.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>厕所</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_LIFE_ENVIRONMENT/slot">
                                <xsl:if test="@code='DE03.00.005.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>禽畜栏</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="structuredBody/PERSON_LIFE_ENVIRONMENT/slot">
                                <xsl:if test="@code='DE03.00.049.00'">
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