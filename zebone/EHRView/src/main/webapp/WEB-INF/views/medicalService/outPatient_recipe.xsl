<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="1.0" encoding="utf-8" standalone="yes"/>
    <xsl:template match="/ClinicalDocument/structuredBody">
        <html>
            <body>
                <table width="720px" border="0">
                    <tr>
                        <td>
                            <strong>门（急）诊号：</strong>
                            <xsl:for-each select="PRESCRIPTIONS_INFORMATION/slot">
                                <xsl:if test="@name='门（急）诊号'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </td>
                    </tr>
                </table>
                <table width="720px" class="jgridDataTable" style="">
                    <tr>
                        <td><div align="center"><strong>患者姓名</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="PRESCRIPTIONS_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.039.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td ><div align="center"><strong>性别</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="PRESCRIPTIONS_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.040.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                        <td><div align="center"><strong>年龄</strong></div></td>
                        <td><div align="center">
                            <xsl:for-each select="PRESCRIPTIONS_INFORMATION/slot">
                                <xsl:if test="@code='DE02.01.026.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </div></td>
                    </tr>
                </table>
                <table width="720px" class="jgridDataTable" style="">
                    <tr>
                        <td width="20">
                            <xsl:attribute name="rowspan">
                                <xsl:value-of select="count(PRESCRIPTION_DETAILS)+1"/>
                            </xsl:attribute>
                            <div align="center"><strong>处方详细信息</strong></div></td>
                        <td width="50" class="header"><div align="center"><strong>组号</strong></div></td>
                        <td width="80" class="header"><div align="center"><strong>中心药品代码</strong></div></td>
                        <td width="103" class="header"><div align="center"><strong>药物名称</strong></div></td>
                        <td class="header"><div align="center"><strong>药物规格</strong></div></td>
                        <td class="header"><div align="center"><strong>药物类型</strong></div></td>
                        <td class="header"><div align="center"><strong>使用频率</strong></div></td>
                        <td class="header" width="76"><div align="center"><strong>使用次剂量</strong></div></td>
                        <td class="header" width="68"><div align="center"><strong>使用途径</strong></div></td>
                        <td class="header" width="64"><div align="center"><strong>用药天数</strong></div></td>
                        <td class="header" width="67"><div align="center"><strong>发药数量</strong></div></td>
                    </tr>
                    <xsl:for-each select="PRESCRIPTION_DETAILS">
                        <tr>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='EX00.00.000.81'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td style="width:100px; word-break:break-all"><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='EX00.00.000.82'">
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
                                    <xsl:if test="@code='EX00.00.001.86'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='EX00.00.000.84'">
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
                                    <xsl:if test="@code='EX00.00.000.85'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                            <td><div align="center">
                                <xsl:for-each select="slot">
                                    <xsl:if test="@code='EX00.00.000.86'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>