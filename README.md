Приложение для управления складом компьютерного магазина. Потдерживает добавление, удаление, редактирование и удобный поиск по БД.

БД основана на СУБД Oracle 11.2. Дамп БД *STORE.dmp* находится в корне проекта. Перед импортом необходимо создать нового пользователя. Пример структуры команды выполняющей импорт: `IMP username/password@XE FILE=STORE.dmp FULL=Y LOG=import.log`.

Здесь:

**IMP** - вызов утилиты импорта.

**username** - имя пользователя.

**password** - пароль пользователя *username*.

**XE** - псевдоним экземпляра базы данных к которому следует подключиться. Имя псевдинима можно узнать из файла *tnsnames.ora* расположенного в каталоге ORACLE.

**FULL=Y** - флаг устанавливающий режим полного импорта данных.

**LOG=import.log** - имя лог-файла в который будет записана информация об импорте.

GUI приложения создано при помощи [JavaFX Scenebuilder 2.0](http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html)
