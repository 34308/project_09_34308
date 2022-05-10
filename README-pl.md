<h1 align="center">
<br>
<p align="center">
"BattleShips"
</p>

</h1>

<p >

##Tłumaczenia
- [English](README.md)
- [Polish](README-pl.md)

## Menu
<img src="images/Menu.png" alt="Screenshot">
 <p>W menu gracz może wybrać następujące tryby rozgrywki:</p>
   <p> -Gracz vs Ai</p>
   <p> -Gracz vs Gracz</p>
   <p> -AI vs AI (test AI)</p>
 <p>Lub  wyjść z gry</p>

## Player vs Ai
<p>Pierwszy gracz musi umieścić swoje statki na planszy. Można to zrobić za pomocą myszy, gracz może umieszczać statki lewym przyciskiem myszy i obracać je prawym przyciskiem myszy</p>
<p>Pod planszą gracz może zobaczyć rozmiar swojego następnego statku</p>
<p align="center">
     <img src="images/plshipsPVA.png" alt="Zrzut ekranu">
</p>
<p>Jeśli gracz nie jest zadowolony z umieszczania swoich statków, należy kliknąć 'Reset ships placement'. Ta akcja zresetuje pozycje wszystkich statków </p>
<p>Kiedy gracz umieścił wszystkie swoje statki, możliwe jest kliknięcie przycisku Rozpocznij grę i Rozpocznij grę </p>
<p align="center">
    <img src="images/battlePVA.png" alt="Screenshot">
</p>
<p>Po prawej stronie gracz może zobaczyć swoją planszę ze statkami, które umieścił</p>
<p>Po lewej stronie znajduje się plansza AI z ukrytymi statkami, gracz może „strzelać” do statków przeciwnika, klikając lewym przyciskiem wybrany kwadrat</p>
<p>Po ruchu gracza AI automatycznie wykona własny ruch</p>
<p>gra kończy się, gdy wszystkie statki jednej ze stron są zatopione</p>

## Player vs Player
<p>W tym trybie gry zaleca się, aby gracze podzielili widok na ekranie jakimś rodzajem przeszkody, rozgrywka rozgrywa się na jednym komputerze</p>
  <p>Jak w wcześniejszym trybie rozgrywki gracze na początku powinni rozmieścić swoje statki </p>
<p>pierwszy rozmieszcza swoje statki gracz nr 1, po odznaczeniu 'ready?', do rozstawiania statków może przystąpić gracz 2</p>
<p align="center">
     <img src="images/plShipPVP.png" alt="Zrzut ekranu">
</p>
<p>jeśli obaj gracze umieścili swoje statki i potwierdzili to za pomocą check boxa znajdującego się nad tablicami, przycisk rozpoczęcia gry zostanie odblokowany</p>
<p>jeśli po odznaczeniu check boxa 'ready?', gracz chce zmienić położenie swoich statków, wymagane jest zresetowanie pozycji statków przy użyciu przycisku'Reset ships Placement'</p>
<p align="center">
    <img src="images/battlePVP.png" alt="Screenshot">
</p>
<p> po rozpoczęciu gry obaj gracze mogą widzieć plansze przeciwnika(duża plansza), i ich własne(mała plansza) </p>
<p> Zielone koło wskazuje który z graczy może obecnie wykonać ruch</p>
<p>Tury graczy następują po sobie zaczynając od gracza nr 1</p>
<p>Jak w tradycyjnej grze gracze są zobowiązani do informowania swoich przeciwników gdy ich statek zostanie zatopiony</p>
<p>Gra kończy się gdy wszystkie statki jednego z graczy zostaną zatopione</p>

## AI vs AI

<p>W tym trybie rozgrywki obserwator ma wpływ na przewijanie tury, rozgrywki AI</p>
<p align="center">
    <img src="images/AIBattle.png" alt="Screenshot">
</p>

<p>Gra kończy się gdy wszystkie statki jednego z uczestników zostaną zatopione</p>

## Kilka rzeczy o AI

<h3 align="center">
    AI ships placement
</h3>
<p>AI ładuje nowe koordynaty dla statków z plików nazwanych scheme'number'.txt na przykład scheme1.txt</p>
<p>W każdym z tych plików znajdują się inne koordynaty dla statków, AI losuje jeden z nich i na jego podstawie rozmieszcza statki</p>
<p>Pliki te wyglądają następująco:</p>
00 01 <br>
03 04<br>
06 07<br>
09 19<br>
20 21 22<br>
24 25 26<br>
39 49 59<br>
40 50 60 70<br>
42 52 62 72<br>
44 54 64 74 84 94<br>
<p>Każda para z liczb powyżej reprezentuje położenie pojedynczej części statku, na podstawie ilości par w linijce wiemy jak duży jest dany statek. </p>
<p>Klasa FileValidator sprawdza czy te koordynaty są  poprawne przed umieszczeniem statków na tablicy</p>
<h3 align="center">
    Jak AI celuje i strzela
</h3>
<p>Proces ten może być następująco przedstawiony </p>
<p>1.Wylosuj losową pozycje na planszy (*sprawdź czy Ai może tam strzelać), strzel </p>
<p>2.Jeżeli Ai uderzyło statek gracza wybierz jeden z czterech kierunków i strzel najbliższe pole w tym kierunku </p>
<p>3.Jeżeli strzał chybił wróć do dwójki wybierając inny kierunek,ale jeżeli statek został trafiony kontynuuj strzelanie w tym kierunku</p>
<p>4.Jeżeli strzał spudłował a statek nie został zatopiony wróć do pierwszego trafienia(punkt 2) i kontynuuj strzelanie w odwrotnym kierunku</p>
<p>5.Jeżeli statek został zatopiony wróć do punktu pierwszego</p>
<p>*Ai sprawdza czy wybrane miejsce nie zostało użyte już wcześniej(dokładniej czy na planszy nie ma znacznika trafienia bądź pudła)</p>

## Użyte oprogramowanie
<p>IntelliJ IDEA 2021.3.3</p>
<p>Java: sdk 17</p>
<p>javafx:17.0.2</p>

## Jak uruchomić
<p>Aplikacja powinna być uruchomiona z MainR(main ten jedynie uruchamia main HelloAplication, jednak jest on wymagany aby przekonwertować projekt do .jar, obecny program wbudowany w intelij ma problemy podczas przetwarzania aplikacji która rozszerza Aplication(pakiet Javafx)), jednak uruchomienie jej z HelloAplication także powinno działać poprawnie </p>

## Jak to zrobiliśmy
<p>Na początku zaplanowaliśmy zrobić ten projekt konsolowo/tekstowo jako że były to minimalne wymagania, ale gdy skończyliśmy znacznie przed końcem terminu, postanowiliśmy przerobić projekt aby używał GUI</p>
<p>Przez to w kodzie nadal znajdowały się naleciałości z jego konsolowej wersji, postanowiliśmy ich nie usuwać, a jedynie zakomentować</p>
<p></p>
