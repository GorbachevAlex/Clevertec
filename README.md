# Название: 
Консольное приложение, реализующее финкционал формирования чека в магазине

## Описание: 
при выборе вида товара, его количества, указания скидочной карты и баланса карты - формируется магазинный чек

## Запуск:
команда для запуска приложения: "id-quantity discountCard=xxxx balanceDebitCard=xxxx"
пример команды: "1-1 1-7 2-3 8-6 1-2 20-4 3-7 discountCard=4444 balanceDebitCard=172.46"
обязательные условия:
-команда разделяется пробелом
-между id и quantity ставится префикс "-"
-id - номер товара в списке
-quantity - количество товара из списка
-discountCard=xxxx - скидочная карта (скидка может быть, может не быть). Виды дисконтных карт можно посмотреть в ./main/resources/discount.csv
-balanceDebitCard=xxxx - бюджет (может быть любым, даже отрицательным). Виды товара можно посмотреть в ./main/resources/products.csv
После отправки данной команды будет сформирован чек. Вид чека можно посмотреть в корне проекта ./result.csv

### Контакты
если есть вопросы, свяжитесь со мной
smilebee777@mail.ru
aleksejgorbacev195@gmail.com
