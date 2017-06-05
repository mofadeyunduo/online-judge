function switchLanguage(){
    var name = document.getElementById("languageName");
    var description = document.getElementById("languageDescription");
    var code = document.getElementById("code");
    var index = name.selectedIndex;
    switch(name.options[index].value) {
        case "java":
            description.innerHTML = "语言：Java，输入输出流：System.In 和 System.Out，主类：Main，函数：标准 main 函数。";
            code.innerHTML = document.getElementById("javaCode").innerHTML;
            break;
        case "cpp":
            description.innerHTML = "语言：C++，输入输出流：cin 和 cout，函数：标准 main 函数。";
            code.innerHTML = document.getElementById("cppCode").innerHTML;
            break;
    }
}