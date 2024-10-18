#include <stdlib.h>
#include <stdio.h>
#include <windows.h>
#include <graphics.h>
#define TRUE 1
#define FALSE 0

/* Estructuras y tipos */
typedef struct _nodo {
   struct _nodo *izquierdo;
	 int dato;
   struct _nodo *derecho;
} tipoNodo;

typedef tipoNodo *pNodo;
typedef tipoNodo *Arbol;

void mostrarArbol(Arbol a, int x, int y, int nivel);
void gotoxy(int x,int y);
/* Funciones con árboles: */
/* Insertar en árbol ordenado: */
void Insertar(Arbol *a, int dat);
/* Borrar un elemento: */
void Borrar(Arbol *a, int dat);
/* Función de búsqueda: */
int Buscar(Arbol a, int dat);
/* Comprobar si el árbol está vacío: */
int Vacio(Arbol r);
/* Comprobar si es un nodo hoja: */
int EsHoja(pNodo r);
/* Contar número de nodos: */
int NumeroNodos(Arbol a, int* c);
/* Calcular la altura de un árbol: */
int AlturaArbol(Arbol a, int* altura);
/* Calcular altura de un dato: */
int Altura(Arbol a, int dat);
/* Aplicar una función a cada elemento del árbol: */
void InOrden(Arbol);

/* Funciones auxiliares: */
void Podar(Arbol *a);
void auxContador(Arbol a, int*);
void auxAltura(Arbol a, int, int*);

int main(){
	Arbol ArbolInt=NULL;
	//int gd = DETECT, gm;
	int altura;
	int nnodos;
	int opc,num;
	do{
		gotoxy(45,4); printf("Arboles de busqueda binarias");
		gotoxy(50,7);printf("1. Insertar");
		gotoxy(50,8);printf("2. Mostrar");
		gotoxy(50,9);printf("3. Buscar");
		gotoxy(50,10);printf("4. Eliminar");
		gotoxy(50,11);printf("5. Vaciar");
		gotoxy(50,12);printf("6. Salir");
		gotoxy(50,13);printf("Ingrese una opcion: [   ]\b\b\b");
		scanf("%d",&opc);
		system("cls");
		switch(opc){
			case 1: gotoxy(56,4); printf("Insertar");
					gotoxy(35,8); printf("Digite el numero: ");
					scanf("%d",&num);
					Insertar(&ArbolInt, num);
					gotoxy(35,10); printf("Altura de arbol %d", AlturaArbol(ArbolInt, &altura));
					/* Mostrar el árbol InOrden: */
					gotoxy(35,12); printf("InOrden: ");
					InOrden(ArbolInt);
					break;
			case 2: gotoxy(56,4); printf("Mostrar");
    				//initgraph(&gd, &gm, "C:\\Dev-Cpp\\MinGW64\\lib");
    				initwindow(1600, 900);
					mostrarArbol(ArbolInt, 600, 30, 0);
					getch();
					closegraph();
					break;		
			case 3: gotoxy(57,4); printf("Buscar");
					gotoxy(35,8); printf("Digite el numero: ");
					scanf("%d",&num);
					Buscar(ArbolInt, num);
					break;  
			case 4: gotoxy(56,4); printf("Eliminar");
					gotoxy(35,8); printf("Digite el numero: ");
					scanf("%d",&num);
					Borrar(&ArbolInt, num);
					gotoxy(35,12); printf("InOrden: ");
					InOrden(ArbolInt);
					break;
			case 5: gotoxy(56,4); printf("Vaciar");
					Podar(&ArbolInt);
					gotoxy(35,10); printf("El arbol esta vacio");
					break;   
			case 6: gotoxy(56,15); printf("SALIENDO");
			default: gotoxy(40,20); printf("El numero ingresado no es valido");
		}
		gotoxy(40,20); system("pause");
		system("cls");
	}while(opc != 6);
	
}

/* Poda: borrar todos los nodos a partir de uno, incluido */
void Podar(Arbol *a)
{
   /* Algoritmo recursivo, recorrido en postorden */
   if(*a) {
      Podar(&(*a)->izquierdo); /* Podar izquierdo */
      Podar(&(*a)->derecho);   /* Podar derecho */
      free(*a);                /* Eliminar nodo */
      *a = NULL;
   }
}

/* Insertar un dato en el árbol ABB */
void Insertar(Arbol *a, int dat)
{
   pNodo padre = NULL;
   pNodo actual = *a;

   /* Buscar el dato en el árbol, manteniendo un puntero al nodo padre */
   while(!Vacio(actual) && dat != actual->dato) {
      padre = actual;
      if(dat < actual->dato) actual = actual->izquierdo;
      else if(dat > actual->dato) actual = actual->derecho;
   }

   /* Si se ha encontrado el elemento, regresar sin insertar */
   if(!Vacio(actual)) return;
   /* Si padre es NULL, entonces el árbol estaba vacío, el nuevo nodo será
      el nodo raiz */
   if(Vacio(padre)) {
      *a = (Arbol)malloc(sizeof(tipoNodo));
      (*a)->dato = dat;
      (*a)->izquierdo = (*a)->derecho = NULL;
   }
   /* Si el dato es menor que el que contiene el nodo padre, lo insertamos
      en la rama izquierda */
   else if(dat < padre->dato) {
      actual = (Arbol)malloc(sizeof(tipoNodo));
      padre->izquierdo = actual;
      actual->dato = dat;
      actual->izquierdo = actual->derecho = NULL;
   }
   /* Si el dato es mayor que el que contiene el nodo padre, lo insertamos
      en la rama derecha */
   else if(dat > padre->dato) {
      actual = (Arbol)malloc(sizeof(tipoNodo));
      padre->derecho = actual;
      actual->dato = dat;
      actual->izquierdo = actual->derecho = NULL;
  }
}

void mostrarArbol(Arbol a, int x, int y, int nivel) {
    int radio = 25;
    char valorStr[100];

    if (a == NULL) {
        return;
    }

    int centroX = x;
    int centroY = y;

    // Dibuja un círculo en el centro del nodo con el valor
    setcolor(WHITE);
    circle(centroX, centroY, radio);
    sprintf(valorStr, "%d", a->dato);
    outtextxy(centroX - 3, centroY - 6, valorStr);

    // Coordenadas de los hijos izquierdo y derecho
    int izquierdoX = x - (200 / (nivel + 1));
    int izquierdoY = y + 100;

    int derechoX = x + (200 / (nivel + 1));
    int derechoY = y + 100;

    // Dibuja una línea desde el centro del nodo hasta el hijo izquierdo
    if (a->izquierdo != NULL) {
        setcolor(CYAN);
        line(centroX - radio, centroY + radio, izquierdoX, izquierdoY - radio);
        // FUNCION RECURSIVA para mostrar el subárbol izquierdo
        mostrarArbol(a->izquierdo, izquierdoX, izquierdoY, nivel + 1);
    }

    // Dibuja una línea desde el centro del nodo hasta el hijo derecho
    if (a->derecho != NULL) {
        setcolor(CYAN);
        line(centroX + radio, centroY + radio, derechoX, derechoY - radio);
        // FUNCION RECURSIVA para mostrar el subárbol derecho
        mostrarArbol(a->derecho, derechoX, derechoY, nivel + 1);
    }
}

/* Eliminar un elemento de un árbol ABB */
void Borrar(Arbol *a, int dat)
{
   pNodo padre = NULL;
   pNodo actual;
   pNodo nodo;
   int aux;

   actual = *a;
   /* Mientras sea posible que el valor esté en el árbol */
   while(!Vacio(actual)) {
      if(dat == actual->dato) { /* Si el valor está en el nodo actual */
         if(EsHoja(actual)) { /* Y si además es un nodo hoja: lo borramos */
            if(padre) /* Si tiene padre (no es el nodo raiz) */
               /* Anulamos el puntero que le hace referencia */
               if(padre->derecho == actual) padre->derecho = NULL;
               else if(padre->izquierdo == actual) padre->izquierdo = NULL;
            free(actual); /* Borrar el nodo */
            gotoxy(35,10); printf("El elemento se elimino del arbol");
            actual = NULL;
            return;
         }
         else { /* Si el valor está en el nodo actual, pero no es hoja */
            padre = actual;
            /* Buscar nodo más izquierdo de rama derecha */
            if(actual->derecho) {
               nodo = actual->derecho;
               while(nodo->izquierdo) {
                  padre = nodo;
                  nodo = nodo->izquierdo;
               }
            }
            /* O buscar nodo más derecho de rama izquierda */
            else {
               nodo = actual->izquierdo;
               while(nodo->derecho) {
                  padre = nodo;
                  nodo = nodo->derecho;
               }
            }
            /* Intercambiar valores de no a borrar u nodo encontrado
               y continuar, cerrando el bucle. El nodo encontrado no tiene
               por qué ser un nodo hoja, cerrando el bucle nos aseguramos
               de que sólo se eliminan nodos hoja. */
            aux = actual->dato;
            actual->dato = nodo->dato;
            nodo->dato = aux;
            actual = nodo;
         }
      }
      else { /* Todavía no hemos encontrado el valor, seguir buscándolo */
         padre = actual;
         if(dat > actual->dato) actual = actual->derecho;
         else if(dat < actual->dato) actual = actual->izquierdo;
      }
   }
   gotoxy(35,10); printf("El elemento no se encontro en el arbol");
}

/* Recorrido de árbol en inorden, aplicamos la función func, que tiene
   el prototipo:
   void func(int*);
*/
void InOrden(Arbol a)
{
   if(a->izquierdo) InOrden(a->izquierdo);
   printf("%d, ", a->dato);
   if(a->derecho) InOrden(a->derecho);
}

/* Buscar un valor en el árbol */
int Buscar(Arbol a, int dat)
{
   pNodo actual = a;

   /* Todavía puede aparecer, ya que quedan nodos por mirar */
   while(!Vacio(actual)) {
      if(dat == actual->dato){
      	gotoxy(35,10); printf("El elemento se encuentra en el arbol");
	  	return TRUE; /* dato encontrado */
	  } 
      else if(dat < actual->dato) actual = actual->izquierdo; /* Seguir */
      else if(dat > actual->dato) actual = actual->derecho;
   }
   gotoxy(35,10); printf("El elemento no se encuentra en el arbol");
   return FALSE; /* No está en árbol */
}

/* Calcular la altura del nodo que contiene el dato dat */
int Altura(Arbol a, int dat)
{
   int altura = 0;
   pNodo actual = a;

   /* Todavía puede aparecer, ya que quedan nodos por mirar */
   while(!Vacio(actual)) {
      if(dat == actual->dato) return altura; /* encontrado: devolver altura */
      else {
         altura++; /* Incrementamos la altura, seguimos buscando */
         if(dat < actual->dato) actual = actual->izquierdo;
         else if(dat > actual->dato) actual = actual->derecho;
      }
   }
   return -1; /* No está en árbol, devolver -1 */
}

/* Contar el número de nodos */
int NumeroNodos(Arbol a, int *contador)
{
   *contador = 0;

   auxContador(a, contador); /* Función auxiliar */
   return *contador;
}

/* Función auxiliar para contar nodos. Función recursiva de recorrido en
   preorden, el proceso es aumentar el contador */
void auxContador(Arbol nodo, int *c)
{
   (*c)++; /* Otro nodo */
   /* Continuar recorrido */
   if(nodo->izquierdo) auxContador(nodo->izquierdo, c);
   if(nodo->derecho)   auxContador(nodo->derecho, c);
}

/* Calcular la altura del árbol, que es la altura del nodo de mayor altura. */
int AlturaArbol(Arbol a, int *altura)
{
   *altura = 0;

   auxAltura(a, 0, altura); /* Función auxiliar */
   return *altura;
}

/* Función auxiliar para calcular altura. Función recursiva de recorrido en
   postorden, el proceso es actualizar la altura sólo en nodos hojas de mayor
   altura de la máxima actual */
void auxAltura(pNodo nodo, int a, int *altura)
{
   /* Recorrido postorden */
   if(nodo->izquierdo) auxAltura(nodo->izquierdo, a+1, altura);
   if(nodo->derecho)   auxAltura(nodo->derecho, a+1, altura);
   /* Proceso, si es un nodo hoja, y su altura es mayor que la actual del
      árbol, actualizamos la altura actual del árbol */
   if(EsHoja(nodo) && a > *altura) *altura = a;
}

/* Comprobar si un árbol es vacío */
int Vacio(Arbol r)
{
   return r==NULL;
}

/* Comprobar si un nodo es hoja */
int EsHoja(pNodo r)
{
   return !r->derecho && !r->izquierdo;
}

void gotoxy(int x,int y){  
    HANDLE hcon;  
    hcon = GetStdHandle(STD_OUTPUT_HANDLE);  
    COORD dwPos;  
    dwPos.X = x;  
    dwPos.Y= y;  
    SetConsoleCursorPosition(hcon,dwPos);  
}  