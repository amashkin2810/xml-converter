<?xml version="1.0" encoding="Windows-1251"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" >
<xsl:output method="text" encoding = "Windows-1251" omit-xml-declaration="yes" indent="no"/>
<xsl:template match="/�������">
<xsl:value-of select="concat(1,';',@�������������,';',@����������������,';')"/>
<xsl:for-each select="//����������������">
<xsl:value-of select="concat(����������,'&#xA;')"/>
</xsl:for-each>
<xsl:for-each select="//���������">
<xsl:value-of select="concat(2,';',@���,';',�������,' ',���,' ',��������,';',�����������,';',�����,'&#xA;')"/>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>