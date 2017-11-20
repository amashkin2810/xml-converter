<?xml version="1.0" encoding="Windows-1251"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" >
<xsl:output method="text" encoding = "Windows-1251" omit-xml-declaration="yes" indent="no"/>
<xsl:template match="/СчетаПК">
<xsl:value-of select="concat(1,';',@НомерДоговора,';',@ДатаФормирования,';')"/>
<xsl:for-each select="//КонтрольныеСуммы">
<xsl:value-of select="concat(СуммаИтого,'&#xA;')"/>
</xsl:for-each>
<xsl:for-each select="//Сотрудник">
<xsl:value-of select="concat(2,';',@Нпп,';',Фамилия,' ',Имя,' ',Отчество,';',ЛицевойСчет,';',Сумма,'&#xA;')"/>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>