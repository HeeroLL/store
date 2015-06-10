<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="1.0" encoding="utf-8" standalone="yes"/>
    <xsl:template match="/ClinicalDocument/structuredBody">
        <html>
            <body>
                <table width="720px" border="0">
                    <tr>
                        <td>
                            <strong>姓名：</strong>
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.039.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </td>
                        <td>
                            <div align="right">
                                <strong>门（急）诊号：</strong>
                                <xsl:for-each select="BASIC_INFORMATION/slot">
                                    <xsl:if test="@name='门（急）诊号'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                </table>
                <table width="720px" class="jgridDataTable" style="">
                    <tr>
                        <td width="137"><div align="center"><strong>就诊时间</strong></div></td>
                        <td colspan="5"><div align="center">
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE06.00.062.00'">
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
                        <td><div align="center"><strong>主诉</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE04.01.119.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>现病史</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE02.10.071.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>过敏史描述</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE02.10.022.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>既往史</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE02.10.026.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>体格检查</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE04.10.258.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>主要疾病诊断</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE05.01.024.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td>
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(Z)+1"/>
                            </xsl:attribute>
                            <div align="center"><strong>诊断信息</strong></div></td>
                        <td colspan="2"><div align="center"><strong>序号</strong></div></td>
                        <td colspan="3"><div align="center"><strong>基本诊断代码</strong></div></td>
                    </tr>
                    <xsl:for-each select="Z">
                        <tr>
                            <td colspan="2"><div align="center">
                                <xsl:value-of select="position()"/>
                            </div></td>
                            <td colspan="3"><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='DE05.01.024.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                        </tr>
                    </xsl:for-each>
                    <tr>
                        <td><div align="center"><strong>处理及指导意见</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE06.00.018.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>病人去向</strong></div></td>
                        <td colspan="5"><div>
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='EX00.00.000.64'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                    <tr>
                        <td><div align="center"><strong>服务机构</strong></div></td>
                        <td width="122"><div align="center">
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='EX00.00.000.06'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="66"><div align="center"><strong>就诊科室</strong></div></td>
                        <td width="118"><div align="center">
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='DE08.10.025.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td width="84"> <div align="center"><strong>医生</strong></div></td>
                        <td width="147"><div align="center">
                            <xsl:for-each select="BASIC_INFORMATION/slot">
                                <xsl:if test="@code='EX00.00.000.07'">
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