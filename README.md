Инструкция по запуску
---------------------
1. Создать jar файл вызовом команды "mwn package"
2. Запустить программу с входными параметрами, например:
> java -jar  C:\Users\AlJu\IdeaProjects\СFT\CFT_test_task\target\CFT_test_task-1.0-SNAPSHOT.jar '-d' '-i' out.txt in1.txt in2.txt

- Порядок ввода параметров:
 `Режим сортировки`
 `Тип данных`
 `Имя выходного файла`
 `Имена входных файлов`
- порядок должен строго соблюдаться
- имена входных файлов должны заканчиваться "in1", "in2" и так далее.
- имя выходного файла должно заканчиваться на "out".
- версия Java - 17
- система сборки Maven 4.0.0
