<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="1.0" encoding="utf-8" standalone="yes"/>
    <xsl:template match="/ClinicalDocument/structuredBody">
        <html>
            <body>
                <table width="720px" class="jgridDataTable" style="">
                    <tr>
                        <td width="78"><div align="center"><strong>姓名</strong></div></td>
                        <td width="63"><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.039.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="43"><div align="center"><strong>性别</strong></div></td>
                        <td width="74"><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.040.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="35"><div align="center"><strong>年龄</strong></div></td>
                        <td width="57"><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.026.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="74"><div align="center"><strong>标本代码</strong></div></td>
                        <td width="78"><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@code='EX00.00.001.40'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="79"><div align="center"><strong>检验标本号</strong></div></td>
                        <td width="77" style="width:100px; word-break:break-all"><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@code='DE01.00.003.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>门诊号</strong></div></td>
                        <td colspan="2" style="width:100px; word-break:break-all"><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@code='DE01.00.010.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td colspan="2"><div align="center"><strong>就诊科室</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@name='就诊科室代码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>申请医生</strong></div></td>
                        <td colspan="2"><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@name='申请医生编码'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(INSPECTION_DETAILS)+1"/>
                            </xsl:attribute>
                            <div align="center"><strong>检验详细信息</strong></div></td>
                        <td class="header"><div align="center"><strong>序号</strong></div></td>
                        <td class="header" colspan="2"><div align="center"><strong>检验项目</strong></div></td>
                        <td class="header" colspan="2"><div align="center"><strong>结果</strong></div></td>
                        <td class="header"><div align="center"><strong>单位</strong></div></td>
                        <td class="header"><div align="center"><strong>参考值</strong></div></td>
                        <td class="header" colspan="2"><div align="center"><strong>异常结果提示</strong></div></td>
                    </tr>
                    <xsl:for-each select="INSPECTION_DETAILS">
                        <tr>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='EX00.00.001.48'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td colspan="2"><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='EX00.00.001.42'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td colspan="2"><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='EX00.00.001.49'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE04.30.016.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='EX00.00.001.45'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                    <xsl:if test="@code='EX00.00.001.46'">
                                        -<xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td colspan="2"><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='EX00.00.001.47'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                        </tr>
                    </xsl:for-each>
                </table>
                <table width="720px" class="jgridDataTable" style="">
                    <tr>
                        <td><div align="center"><strong>检验日期</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@code='DE06.00.048.00'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>报告日期</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@code='DE04.50.133.00'">
                                    <xsl:value-of select="substring(value/@displayName,'1','4')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'5','2')"/>-
                                    <xsl:value-of select="substring(value/@displayName,'7','2')"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>核对者</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="INSPECTION_INFORMATION/slot">
                                <xsl:if test="@name='审核医生编码'">
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