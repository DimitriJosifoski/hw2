@echo off
move filter.properties  filter-save.properties
set CS336_COLUMNS=
set CS336_GROUPS=
set CS336_SPACING=         
@echo ==== Filter -1 -2 -3 < data.txt
      java Filter -1 -2 -3 < data.txt
@echo:
@echo ==== Filter -2 4 7 < data.txt
      java Filter -2 4 7 < data.txt
@echo:
@echo ==== Filter 4 -10 7 < data.txt
      java Filter 4 -10 7 < data.txt
@echo:
@echo ==== Filter 6 3 -3 < data.txt
      java Filter 6 3 -3 < data.txt
@echo:
@echo ==== Filter x y z < data.txt
      java Filter x y z < data.txt
@echo:
@echo ==== Filter x 5 7 < data.txt
      java Filter x 5 7 < data.txt
@echo:
@echo ==== Filter 6 bob 7 < data.txt
      java Filter 6 bob 7 < data.txt
@echo:
@echo ==== Filter 4 3 y < data.txt
      java Filter 4 3 y < data.txt
move filter-save.properties  filter.properties
pause
