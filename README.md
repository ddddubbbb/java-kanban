# java-kanban-sprint7-hw

### Задание седьмого спринта.

##### Трекер задач

Как системы контроля версий помогают команде работать с общим кодом, так и трекеры задач
позволяют эффективно организовать совместную работу над задачами. Вам предстоит написать
бэкенд для такого трекера. В итоге должна получиться программа, отвечающая за формирование
модели данных для этой страницы:
![img](img/Untitled-120_1696414230.png)

        💡 Пользователь не будет видеть консоль вашего приложения. Поэтому нужно сделать так, чтобы 
           методы не просто печатали что-то в консоль, но и возвращали объекты нужных типов.
           Вы можете добавить консольный вывод для самопроверки в класcе Main, но на работу методов он 
           влиять не должен.

<details> <summary> ТЗ </summary>  

Нужно усовершенствовать приложение "Трекер задач" из ТЗ для 3, 4, 5, 6 спринта (ветки: java-kanban-sprint3/4/5/6
-hw).

Без тестов нельзя проверить программу и убедиться, что всё работает именно так, как задумано. В трекере уже есть код
проверки — он содержится в методах main. На основе этого кода вам предстоит написать тесты для менеджеров и задач.
Также в этом спринте вы добавите новую функциональность: приложение сможет расставлять задачи по приоритету и проверять,
не пересекаются ли они по времени выполнения.

### Добавьте JUnit в проект

Прежде чем приступать к написанию тестов, добавьте поддержку JUnit в проект. Для этого выполните в IntelliJ IDEA
следующие действия.

1. Откройте любой класс, например Epic.
2. Нажмите Ctrl+Shift+T. В выпадающем меню выберите пункт Create test (англ. «Создать тест»). В появившемся окне нажмите
   кнопку OK — тест будет размещён в той же папке.
   ![img](img/1.png)
3. В меню выбора теста (Testing library) выберите JUnit5, а затем нажмите кнопку Fix (англ. «Исправить»).
   ![img](img/2.png)
4. Скачайте библиотеку в папку lib. Поставьте галочку около пункта Download to (англ. «Скачать в...») и нажмите кнопку
   OK,
   чтобы подтвердить создание теста.
   ![img](img/3.png)
5. После этого откроется файл EpicTest. Можно переходить к написанию тестов.

       💡 Проверьте, что все библиотеки загрузились в папку lib.

![img](img/4.png)

### Покройте код тестами

Ваша цель — написать отдельный тест для каждого публичного метода: стандартный кейс его работы и граничные случаи.

#### Потребуются следующие тесты.

1. Для расчёта статуса Epic. Граничные условия:

* a. Пустой список подзадач.
* b. Все подзадачи со статусом NEW.
* c. Все подзадачи со статусом DONE.
* d. Подзадачи со статусами NEW и DONE.
* e. Подзадачи со статусом IN_PROGRESS.

2. Для двух менеджеров задач InMemoryTasksManager и FileBackedTasksManager.

* Чтобы избежать дублирования кода, необходим базовый класс с тестами на каждый метод из интерфейса abstract class
  TaskManagerTest<T extends TaskManager>.
* Для подзадач нужно дополнительно проверить наличие эпика, а для эпика — расчёт статуса.
* Для каждого метода нужно проверить его работу:
    * a. Со стандартным поведением.
    * b. С пустым списком задач.
    * c. С неверным идентификатором задачи (пустой и/или несуществующий идентификатор).

3. Для HistoryManager — тесты для всех методов интерфейса. Граничные условия:

* a. Пустая история задач.
* b. Дублирование.
* с. Удаление из истории: начало, середина, конец.

4. Дополнительно для FileBackedTasksManager — проверка работы по сохранению и восстановлению состояния. Граничные
   условия:

* a. Пустой список задач.
* b. Эпик без подзадач.
* c. Пустой список истории.

После написания тестов ещё раз проверьте их наличие по списку. Убедитесь, что они работают.

### Добавьте продолжительность и дату старта

Добавьте новые поля в задачи:

* duration — продолжительность задачи, оценка того, сколько времени она займёт в минутах (число);
* startTime — дата, когда предполагается приступить к выполнению задачи.
* getEndTime() — время завершения задачи, которое рассчитывается исходя из startTime и duration.

Менять сигнатуры методов интерфейса TaskManager не понадобится: при создании или обновлении задач все его методы будут
принимать и возвращать объект, в который вы добавите два новых поля.

С классом Epic придётся поработать дополнительно. Продолжительность эпика — сумма продолжительности всех его подзадач.
Время начала — дата старта самой ранней подзадачи, а время завершения — время окончания самой поздней из задач. Новые
поля duration и startTime этого класса будут расчётные — аналогично полю статус. Для реализации getEndTime() удобно
добавить поле endTime в Epic и рассчитать его вместе с другими полями.

Не забудьте также доработать опцию сохранения состояния в файл: добавьте в сериализацию новые поля.

Добавьте в тесты проверку новых полей.

### Выведите список задач в порядке приоритета

Отсортируйте все задачи по приоритету — то есть по startTime. Если дата старта не задана, добавьте задачу в конец списка
задач, подзадач, отсортированных по startTime. Напишите новый метод getPrioritizedTasks, возвращающий список задач и
подзадач в заданном порядке.
Предполагается, что пользователь будет часто запрашивать этот список задач и подзадач, поэтому подберите подходящую
структуру данных для хранения. Сложность получения должна быть уменьшена с O(n log n) до O(n).

### Проверьте пересечения
Предполагается, что пользователь будет выполнять не более одной задачи за раз. Научите трекер проверять, что задачи и
подзадачи не пересекаются по времени выполнения. Добавьте валидацию во время создания или изменения задач, подзадач.

</details>