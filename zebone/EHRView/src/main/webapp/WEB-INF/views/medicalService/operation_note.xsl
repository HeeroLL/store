<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="1.0" encoding="utf-8" standalone="yes"/>
    <xsl:template match="/ClinicalDocument/structuredBody">
        <html>
            <body>
                <table width="720px" border="0">
                    <tr>
                        <td>
                            <strong>科室：</strong>
                        </td>
                        <td>
                            <div align="right">
                                <strong>门（急）诊号：</strong>
                                <xsl:for-each select="BASIC_INFORMATION/slot">
                                    <xsl:if test="@name='门诊号'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                </table>

                <table width="720px" class="jgridDataTable" style="">
                    <tr>
                        <td width="60"><div align="center"><strong>姓名</strong></div></td>
                        <td width="76"><div align="center">
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.039.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="79"><div align="center"><strong>性别</strong></div></td>
                        <td width="101"><div align="center">
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.040.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="88"><div align="center"><strong>年龄</strong></div></td>
                        <td width="79"><div align="center">
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.026.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="98"><div align="center"><strong>病案号</strong></div></td>
                        <td width="105" style="width:100px; word-break:break-all"><div align="center">
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE01.00.004.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>手术开始时间</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.001.26'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    <xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;</xsl:text>
                                    <xsl:value-of select="substring(value/@displayName,'10','2')"/>:
                                    <xsl:value-of select="substring(value/@displayName,'12','2')"/>:
                                    <xsl:value-of select="substring(value/@displayName,'14','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>手术结束时间</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.001.25'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                    <xsl:text disable-output-escaping="yes">&amp;nbsp;&amp;nbsp;</xsl:text>
                                    <xsl:value-of select="substring(value/@displayName,'10','2')"/>:
                                    <xsl:value-of select="substring(value/@displayName,'12','2')"/>:
                                    <xsl:value-of select="substring(value/@displayName,'14','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>术前诊断</strong></div></td>
                        <td colspan="6"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@name='术前诊断'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>术后诊断</strong></div></td>
                        <td colspan="6"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@name='术后诊断'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>手术及操作名称</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@code='DE06.00.094.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>手术操作部位</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@code='DE06.00.187.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>麻醉医生</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@name='麻醉医生编码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>麻醉方法</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@code='DE06.00.073.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>手术医生</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@name='手术医生编码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>手术医生一助</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@name='手术医生一助编码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>手术医生二助</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@name='手术医生二助编码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>手术结果</strong></div></td>
                        <td colspan="6"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@code='EX00.00.001.27'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><strong>手术切口类别</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@code='DE06.00.257.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>切口愈合等级</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="OPERATION_DETAILS/slot">
                                <xsl:if test="@code='DE05.10.147.00'">
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