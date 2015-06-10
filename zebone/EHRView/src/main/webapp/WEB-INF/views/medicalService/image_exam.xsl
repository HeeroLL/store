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
                            <xsl:for-each select="INSPECTION_REPORT/slot">
                                <xsl:if test="@code='DE01.00.010.00'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </td>
                    </tr>
                </table>

                <table width="720px" class="jgridDataTable" style="">
                    <tr>
                        <td width="91">
                            <div align="center"><strong>患者姓名</strong></div>
                        </td>
                        <td width="143">
                            <div align="center">
                                <xsl:for-each select="INSPECTION_REPORT/slot">
                                    <xsl:if test="@code='DE02.01.039.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td width="100">
                            <div align="center"><strong>性别</strong></div>
                        </td>
                        <td width="99">
                            <div align="center">
                                <xsl:for-each select="INSPECTION_REPORT/slot">
                                    <xsl:if test="@code='DE02.01.040.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td width="117">
                            <div align="center"><strong>年龄</strong></div>
                        </td>
                        <td width="124">
                            <div align="center">
                                <xsl:for-each select="INSPECTION_REPORT/slot">
                                    <xsl:if test="@code='DE02.01.026.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center"><strong>申请医生</strong></div>
                        </td>
                        <td>
                            <div align="center">
                                <xsl:for-each select="INSPECTION_REPORT/slot">
                                    <xsl:if test="@name='申请医生编码'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>检查部位</strong></div>
                        </td>
                        <td>
                            <div align="center">
                                <xsl:for-each select="INSPECTION_REPORT/slot">
                                    <xsl:if test="@code='DE06.00.187.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                        <td>
                            <div align="center"><strong>检查项目</strong></div>
                        </td>
                        <td>
                            <div align="center">
                                <xsl:for-each select="INSPECTION_REPORT/slot">
                                    <xsl:if test="@code='DE02.10.031.00'">
                                        <xsl:value-of select="value/@displayName"/>
                                    </xsl:if>
                                </xsl:for-each>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center">
                                <strong>检查所见</strong>
                            </div>
                        </td>
                        <td colspan="5">
                            <xsl:for-each select="INSPECTION_REPORT/slot">
                                <xsl:if test="@code='EX00.00.001.34'">
                                    <xsl:value-of select="value/@displayName"/>
                                </xsl:if>
                            </xsl:for-each>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center">
                                <strong>检查诊断</strong>
                            </div>
                        </td>
                        <td colspan="5">
                            <xsl:for-each select="INSPECTION_REPORT/slot">
                                <xsl:if test="@code='EX00.00.001.35'">
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