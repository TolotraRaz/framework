@echo off
echo ========================================
echo COMPILATION DU FRAMEWORK
echo ========================================

cd /d %~dp0

echo Nettoyage...
rmdir build /S /Q 2>nul
rmdir dist /S /Q 2>nul

mkdir build\classes
mkdir dist

echo Compilation du framework...
javac -cp "C:\Users\tolot\OneDrive\Bureau\ITU\S5\Technique_d_acces_reseaux_Clustering\Codes\Framework\core\lib\servlet-api.jar" ^
      -d build\classes src\framework\FrontController.java

if errorlevel 1 (
    echo ERREUR: Echec de la compilation du framework!
    pause
    exit /b 1
)

echo Création du JAR framework.jar...
jar -cvf dist\framework.jar -C build\classes .

echo ========================================
echo FRAMEWORK COMPILÉ AVEC SUCCÈS !
echo ========================================
pause