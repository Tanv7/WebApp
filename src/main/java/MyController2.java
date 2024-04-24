
    @Controller
    public class MyController2 {
        //метод, который умеет выводить форму с кнопкой
        @GetMapping("/entername")
        public String entername() {
            return "entername";
        }

        //метод, который умеет выводить форму с кнопкой И обрабатывать запрос на этой кнопке
        @GetMapping("/enterandsay")
        public String enterandsay(@RequestParam(name = "name", required = false) String n, Model model) {
            model.addAttribute("name", n);
            return "enter_and_say";
        }

        @GetMapping("/sum")//цепляет функцию к адресу страницы на сайте
        //метод возвращает шаблон и принимает на вход 2 параметра из запроса
        public String sum(@RequestParam(name = "a", defaultValue = "0") String a,
                          @RequestParam(name = "b", defaultValue = "0") String b,
                          Model model/*дополнительный парметр - словарь для испоьзования в шаблоне*/) {
            try {
                int x = Integer.parseInt(a);
                int y = Integer.parseInt(b);
                model.addAttribute("result", x + y);
            }
            catch (NumberFormatException e){
                model.addAttribute("errorMsg", "это было не число");
            }
            return "sum";
        }
    }

