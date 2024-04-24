
@Controller
public class MyController1 {
    @GetMapping("/hello")   //реагировать на http запрос localhost:8282/hello  или <IP-адрес>:<Port>/hello
    public String greeting()
    {
        return "hello";  //использовать шаблон hello.html из папки ресурсов
    }

    @GetMapping("/sayname") //реагировать на запрос /syname?name=Вася  или /syname
    public String say(@RequestParam(name="name", required=false)String name, Model model)
    {
        System.out.println("получен параметр "+name);   //чисто для отладки - вывод  в консоль сервера
        model.asMap().put("name", name);                //помещаем имя в словарь для использования шаблонизатором Thymeleaf
        int age = (int) (Math.random()*99)+1;
        model.asMap().put("age", age);                  //помещаем число в словарь
        return "sayname";                       //использовать шаблон sayname.html из папки ресурсов
    }

    @GetMapping("/readfile")     //реагировать на запрос /readfile?fname=c.txt
    public String readfile(@RequestParam(name="fname") String fname, Model model){
        model.asMap().put("fname", fname);
        try {
            fname = "src//main//resources//files//"+fname;  //корректируем имя файла с учетом заранее известного каталога
            Scanner scanner = new Scanner(new File(fname));
            String s = scanner.nextLine();
            scanner.close();
            model.addAttribute("first_line", s);        //помещаем прочитанную строчку в словарь
        }
        catch (Exception e){                            //если не удалось окрыть файл
            model.addAttribute("error", e.getMessage());    //помещаем сообщение об ошибке в словарь
        }
        return "readfile";                     //использовать шаблон readfile.html
    }

}

