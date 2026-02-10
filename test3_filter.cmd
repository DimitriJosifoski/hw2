@rem Source <number-of-lines> <numbers-per-line>
@rem Filter <columns> <precision> <groups>
@rem
@echo off
move filter.properties  filter-save.properties
@echo ====  Source 5 6        Filter 6 4 0
java        Source 5 6 | java Filter 6 4 0
@echo:
@echo ====  Source 6 5        Filter 6 4 0
java        Source 6 5 | java Filter 6 4 0
@echo:
@echo ====  Source 2 10        Filter 2 2 0
java        Source 2 10 | java Filter 2 2 0
@echo:
@echo ====  Source 10 2        Filter 2 2 0
java        Source 10 2 | java Filter 2 2 0
@echo:
@echo ====  Source 5 10        Filter 5 3 10
java        Source 5 10 | java Filter 5 3 10
@echo:
@echo ====  Source 10 5        Filter 5 3 10
java        Source 10 5 | java Filter 5 3 10
move filter-save.properties  filter.properties
pause
