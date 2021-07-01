# file-model-java
Convert JSON, XLSX and CSV files with more effort
The core of the project is help to extract data import or export with microservices (REST)

Methods can be used in many ways, without much customization
## FileParams
Core params, which is used as help for file customization
- There are two main params: `filePath` and `fileName` (no need of file extension)
- Static params for support when defining FileParams
- No direct usage in conversions, only for defining certain params
###ExcelParams
Params for convert **JSON** string(s) to **XLSX** file (or vice versa)
###CsvParams
Params for convert **JSON** string to **CSV** file (or vice versa)
## Converters
### Csv2Json(...)
Converts a csv file to JSON String
### Json2Csv(...)
Converts a JSON String to csv file
### Excel2Json(...)
Converts a xlsx file to JSON List
### Json2Excel(...)
Converts a JSON String or JSON List to xlsx file
## Notes
- All converters need the model Params
- See [test packages](src/test/java) for reference
- Still work in progress (optimization / more functions)
- Any problems or suggestions reported will be helpful
