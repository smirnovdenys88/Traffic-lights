# Traffic-lights
Светофор
Необходимо реализовать светофор на оживленном перекрестке и отчетность по результатам его действия.
Раз в 20 секунд происходит смена сигнала светофора
В случайном порядке создаются случаи проезда светофора автомобилями с каждой стороны (со случайным интервалом до 5 секунд)
В случае если сигнал светофора разрешает проезд, должен быть зарегистрирован удачный проезд в базе данных
В случае если сигнал светофора запрещает проезд, проезд должен регистрироваться и сохраняться в очередь, после смены сигнала светофора, все случаи из очереди проезжают в том порядке, в котором поступили
Рест апи должен возвращать ситуацию на дороге в данный момент (сколько автомобилей ожидает проезда, сколько проехали за то время, пока горит разрещающий сигнал)

Дополнительное задание состоит в отслеживании коллизий, нужно реализовать 2 подхода
1. Создать ситуацию, когда автомобиль проехал на запрещающий сигнал светофора (многопоточность), зарегистрировать проезд в бд и сделать рестп вызов, который создает репорт по этим ситуациям на данном участке (репорт по таким ситуациям за весь промежуток времени/за заданый промежуток)
2. Придумать решение для данной ситуации, как обеспечить безопасность проезда (в проекте должны присутствовать оба варианта)