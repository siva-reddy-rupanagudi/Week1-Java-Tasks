package week1.task8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {
    static  String status="";
    public static void main(String[] args) {
        Filedata f = new Filedata(
                "report.csv",
                "csv",
                2.5,
                "Quarterly financial report content...",
                "Siva Kumar"
        );
        FileProcessor csvProcessing = (fileData ->{processData(f);});
        FileProcessor jsonProcessing = (fileData ->{processData(f);});
        FileProcessor xmlProcessing = (fileData ->{processData(f);});
        FileProcessor txtProcessing = (fileData ->{processData(f);});
        Map<String,FileProcessor> processorMap=new HashMap<>();
        processorMap.put("CSV",csvProcessing);
        processorMap.put("JSON",jsonProcessing);
        processorMap.put("XML",xmlProcessing);
        processorMap.put("TXT",txtProcessing);
        if(f.getFileName().substring(f.getFileName().length()-3).equalsIgnoreCase(f.getFileType())){
            switch (f.getFileType().toUpperCase()){
                case "CSV":
                    processorMap.get("CSV").process(f);
                    break;
                case "JSON":
                    processorMap.get("JSON").process(f);
                    break;
                case "XML":
                    processorMap.get("XML").process(f);
                    break;
                case "TXT":
                    processorMap.get("TXT").process(f);
                    break;
                default:
                    System.out.println("Invalid File Type");
                    break;
            }
        }else {
            System.out.println("FileType and File extension mismatch");
        }

    }
    public static void processData(Filedata f){

        BiConsumer<String,String> displayFile=(str,statu)-> System.out.println(str+": "+statu);
        Consumer<Filedata> display=filedata -> {
          displayFile.accept("File Name: ",filedata.getFileName());
          displayFile.accept("Validation: ",status);
          displayFile.accept("Processor Selected: ",f.getFileType().toUpperCase()+" Processor");
          displayFile.accept(f.getFileType().toUpperCase()," Records Processed Successfully");
        };
        Predicate<Filedata> isValidSize=(file)->file.getFileSizeInMb()<5;
        Predicate<Filedata> isValidType=(file)->file.getFileType().equalsIgnoreCase("csv") || file.getFileType().equalsIgnoreCase("json") || file.getFileType().equalsIgnoreCase("xml") || file.getFileType().equalsIgnoreCase("txt");
        Predicate<Filedata> isValidContent=filedata -> !filedata.getContent().isBlank() && filedata.getContent()!=null;
        Predicate<Filedata> isValidUser=filedata -> !filedata.getUploadedBy().isBlank() && filedata.getUploadedBy()!=null;
         if (isValidSize.and(isValidContent).and(isValidUser).and(isValidType).test(f)){
              status="SUCESS";
              display.accept(f);
         }else {
             status="FAILED";
             display.accept(f);
         }

    }
}
