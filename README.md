# file-utils-java
Convert JSON to XLSX/CSV files with more effort (or the other way around)

Methods can be used in many ways, without much customization

There are many uses, since generating a spreadsheet to generate graphs in legacy systens, to consuming spreadsheet to sync to the server
## how to import
Copy and paste the code below for using in your project:
```xml
<dependency>
<groupId>org.felipimz</groupId>
<artifactId>file-utils-java</artifactId>
<version>2.2.1</version>
</dependency>
```
## FileParams
Core params, which is used as help for file customization
- There are two main params: `filePath` and `fileName` (no need of file extension)
- No direct usage in conversions, only for defining common params
- Three secondary customizations: `logDateTime`, `columnTypes`, `datePattern`
###ExcelParams
Params for convert **JSON** string(s) to **XLSX** file (or vice versa)
- Exclusive secondary customizations: `hasFilter`, `freezeHeader`, `autoSizeColumn`, `sheetNames`, `headerBold`
###CsvParams
Params for convert **JSON** string to **CSV** file (or vice versa)
- Exclusive secondary customization: `separator`
###JsonParams
Params for manipulation JSON String
## Converters
### Csv2Json _generate(csvParams)_
Converts a csv file to JSON String
### Json2Csv _generate(json, csvParams)_
Converts a JSON String to csv file
### Excel2Json _generate(excelParams)_
Converts a xlsx file to JSON List
### Json2Excel _generate(json, excelParams)_, _generate(json list, excelParams)_
Converts a JSON String or JSON List to xlsx file
## JsonUtil
- Can be used for export a formatted json file, using the method _export(json, jsonParams)_
- Can be used for auto column types generating, using the method _generateColumnTypes(json, datePattern)_
  - Note that is need to create a `List<List<ColumnType>>` when generating it
  - `datePattern` can be null  
## Notes
- All converters need the model Params
- See [test packages](src/test/java) for reference
- Any problems or suggestions will be helpful
