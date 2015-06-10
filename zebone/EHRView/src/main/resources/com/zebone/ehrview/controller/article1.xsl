<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html"/>
  <xsl:template match="/">
  	<html>
  		<head>
  			<style>
  				span{
  					margin:10px 10px 10px 10px;
  				}
  				.partInfo{
  					border:1px solid blue;
  					margin:10px;
  				}
  			</style>
  		</head>
  		<body>
		  	<div class="healthInfo">
		       <xsl:apply-templates select="healthRecord/patientInfo"/>
		       <xsl:apply-templates select="healthRecord/hypertension"/>
		       <xsl:apply-templates select="healthRecord/complication"/>
		     </div>
	     </body>
     </html>
  </xsl:template>
  
  <xsl:template match="patientInfo">
   	<div class="partInfo healthInfo">
       <xsl:apply-templates/>
    </div>
  </xsl:template>
  
  <xsl:template match="hypertension">
   	<div class="partInfo hypertension">
       <xsl:apply-templates/>
    </div>
  </xsl:template>
  
  <xsl:template match="complication">
   	<div class="partInfo complication">
       <xsl:apply-templates/>
    </div>
  </xsl:template>
  
  <xsl:template match="item">
  	 <span style="font-weight:bold;">
         <xsl:value-of select="@key"/>:
     </span>
     <span>
         <xsl:value-of select="."/>
     </span>
  </xsl:template>
  
</xsl:stylesheet>


