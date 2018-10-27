**Для запуска тесто** 

``-Dtest=FirstTest -Dbrowser=opera -DpathDriver=/path/to/operadriver.exe -DpathBrowser=/path/to/opera.exe verify``

**Пояснения переменных**
* _Dbrowser_ - название браузера для запуска (opera/chrome/firefox);
* _-DpathDriver_ - необязателен, если пути к драйверам прописаны в "PATH";
* _-DpathBrowser_ - нужен только для запуска Оперы.

**Примеры**
1) `mvn -Dtest=FirstTest -Dbrowser=chrome -DpathDriver=C:/Users/nox757/Downloads/chromedriver.exe verify`
2) `mvn -Dtest=FirstTest -Dbrowser=firefox -DpathDriver=C:\Users\nox757\Downloads\geckodriver.exe verify`
3) `mvn -Dtest=FirstTest -Dbrowser=opera -DpathDriver=C:\Users\nox757\Downloads\operadriver.exe -DpathBrowser=C:\Users\nox757\AppData\Local\Programs\Opera\56.0.3051.70\opera.exe verify`