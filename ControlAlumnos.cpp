#include<stdio.h>
#include<conio.h>
#include<stdlib.h>
#include<string.h>
#include <windows.h>  


struct info{
	char nombre[40];
	int edad;
	char carrera[30];
	int semestre;
};
struct nodo{
	int matricula;
	struct info dir;
	struct nodo *liga;
};

struct nodo *crea();
struct nodo *mostrar(struct nodo *p);
struct nodo *datos(struct nodo *p);
struct nodo *buscar(struct nodo *p);
struct nodo *modificar(struct nodo *p);
struct nodo *ordenar(struct nodo *p);
void abrir();
struct nodo *guardar(struct nodo *p);
int menu();
int respuesta();
void gotoxy(int x,int y);

int main(){
	struct nodo *p=NULL,*q=NULL;
	int opcion,band=0,res;
	do{
		opcion=menu();
		switch(opcion){
			case 1:
				if(band == 0){
					p = crea();
					p->liga=NULL;
					datos(p);
					band=1;
				}else{
					q = crea();	
					q->liga=p;
					datos(q);
					p=q;
				}
				break;
			case 2:
				q=buscar(p);
				if(q != NULL){
					gotoxy(43,12);printf("Desea eliminar los datos del alumno");
					res=respuesta();
					if(res==1) free(q);
				}
				break;
			case 3: 
				buscar(p);
				gotoxy(39,13); system("pause");
				break;
			case 4: 
				modificar(p);
				break;
			case 5:
				ordenar(p);
				break;
			case 6:
				break;
			case 7:
				abrir();
				break;
		}
	}while(opcion!=8);
	
	
}

struct nodo *crea(){
	struct nodo *paux;
	paux=(struct nodo *) malloc(sizeof(struct nodo));
	return paux;
}

struct nodo *mostrar(struct nodo *p){
	gotoxy(49,6);printf("Matricula: ");
	gotoxy(62,6);printf("%d",p->matricula);
	gotoxy(49,7);printf("Nombre: ");
	gotoxy(62,7);puts(p->dir.nombre);
	gotoxy(49,8);printf("Edad: ");
	gotoxy(62,8);printf("%d",p->dir.edad);
	gotoxy(49,9);printf("Carrera: ");
	gotoxy(62,9);puts(p->dir.carrera);
	gotoxy(49,10);printf("Semestre: ");
	gotoxy(62,10);printf("%d",p->dir.semestre);
}

struct nodo *datos(struct nodo *p){
	struct nodo *aux;
	int band=0;
	do{
		gotoxy(35,6); printf("Ingrese la matricula del alumno: ");
		gotoxy(69,6); scanf("%d%*c",&p->matricula);
		aux=p->liga;
		while(aux != NULL){
			if(aux->matricula == p->matricula){
				gotoxy(30,13);printf("La matricula ya esta registrada, ingrese una nueva matricula");
				Sleep(1000);
				gotoxy(30,13);printf("                                                             ");
				gotoxy(69,6);printf(" ");
				band = 1; 
				break;
			}
			band=0;
			aux=aux->liga;
		}	
	}while(band!=0);
	gotoxy(35,7); printf("Ingrese el nombre del alumno: ");
	gotoxy(69,7);gets(p->dir.nombre);
	gotoxy(35,8); printf("Ingrese la edad del alumno: ");
	gotoxy(69,8);scanf("%d%*c",&p->dir.edad);
	gotoxy(35,9);printf("Ingrese la carrera del alumno: ");
	gotoxy(69,9);gets(p->dir.carrera);
	gotoxy(35,10);printf("Ingrese el semestre del alumno: ");
	gotoxy(69,10);scanf("%d%*c",&p->dir.semestre);
	gotoxy(35,13); system("pause");
}
struct nodo *buscar(struct nodo *p){
	struct nodo *aux;
	int matricula;
	int band=0;
	aux = p;
	gotoxy(42,4); printf("Ingrese la matricula del alumno: ");
	scanf("%d%*c",&matricula);
	while(aux != NULL){
		if(aux->matricula == matricula){
			mostrar(aux);
			band=1;
			return aux;
		}
		else
			aux = aux->liga;
	}
	if(band == 0){
		gotoxy(45,8); printf("El alumno no esta registrado");
		gotoxy(39,13); system("pause");
	} 
	return NULL;
}

struct nodo *modificar(struct nodo *q){
	struct nodo *p=NULL;
	int opcion,res;
    char tecla;
	p=buscar(q);
	if(p!=NULL){
		do{
    		opcion = 1;
    		res = 0;
 			do{
				gotoxy(45,15);printf("Presione [ENTER] para continuar");
				gotoxy(61,6+opcion); printf("");
				tecla = getch();
        		switch (tecla) {
            		case 72:  
                		if (opcion > 1) opcion--;
                		break;
            		case 80: 
                		if (opcion < 4) opcion++;
                		break;										
        		}
			}while(tecla != 13);
			gotoxy(62,6+opcion); printf("                                 ");
			switch(opcion){
				case 1:
					gotoxy(62,7);gets(p->dir.nombre);
					break;
				case 2:
					gotoxy(62,8);scanf("%d%*c",&p->dir.edad);
					break;
				case 3:
					gotoxy(62,9);gets(p->dir.carrera);
					break;
				case 4:
					gotoxy(62,10);scanf("%d%*c",&p->dir.semestre);
					break;
			}
			gotoxy(43,12);printf("Desea modificar otro dato del alumno");
			res= respuesta();
		}while(res!=2);
	}
    
}

struct nodo *ordenar(struct nodo *p){
	struct nodo *q,*pa;
	struct info aux;
	int aux_m,band,cont=0;
	int z=0,y=0;
	q=p;
	while(q!=NULL){
		q=q->liga;
		cont++;
	}
	if(cont%2==0){
		z=60-((12*((cont/2)-1))+6);
	}else{
		z=60-(((cont-1)/2)*12);
	}
	q=p;
	while(q != NULL){
		gotoxy(z+(y*12),6); printf("%d",q->matricula);
 		q=q->liga;
 		y++;
	}
	gotoxy(39,13); system("pause");
	do{
		band=0;
		q=p;
		pa=q->liga;
		while(pa!=NULL){
			if(q->matricula > pa->matricula){
				aux=q->dir;
				aux_m= q->matricula;
				q->dir=pa->dir;
				q->matricula=pa->matricula;
				pa->dir=aux;
				pa->matricula=aux_m;
				band=1;
			}
			q=pa;
			pa=q->liga;
		}
	}while(band!=0);
}
struct nodo *guardar(struct nodo *p){
	FILE * flujo = fopen("datos.txt", "a");
	if(flujo == NULL){
		perro("Error en la creacion del Archivo\n");
	}else{
		fprintf(flujo,"%d",p->matricula);
		fputc('\n',flujo);
		fprintf(flujo,"%d",p-dir)
	}
}

void abrir(){
	int i,j;
	char caracter;
	FILE * flujo = fopen("datos.txt", "rb");
	if (flujo == NULL)
		perror("Error en la apertura del archivo");
	i=0;
	j=0;
	while(feof(flujo) == 0){
		caracter = fgetc(flujo);
		if(caracter == '\n'){
			j++;
			i=-1;
		}
		gotoxy(42+i,7+j); printf("%c",caracter);
		i++;
	}
	fclose(flujo);
	gotoxy(42,26); printf("Se ha leido el archivo correctamente");
	gotoxy(42,28); system("pause");
	system("cls");
}


int menu(){
	int opcion = 1;
    char tecla;
	do{
		system("cls");
		gotoxy(48,8); printf("ALTA............[   ]");
		gotoxy(48,9); printf("BAJA............[   ]");
		gotoxy(48,10);printf("CONSULTA........[   ]");
		gotoxy(48,11);printf("MODIFICACIONES..[   ]");
		gotoxy(48,12);printf("ORDENAR.........[   ]");
		gotoxy(48,13);printf("GUARDAR.........[   ]");
		gotoxy(48,14);printf("ABRIR...........[   ]");
		gotoxy(48,15);printf("SALIR...........[   ]");
		gotoxy(43,17);printf("Presione [ENTER] para continuar");
		gotoxy(66,7+opcion); printf("");
		tecla = getch();
        switch (tecla) {
            case 72:  
                if (opcion > 1) opcion--;
                break;
            case 80: 
                if (opcion < 8) opcion++;
                break;
            case 'a': 
				opcion = 1;
				tecla = 13;
				break;
            case 'b': 
				opcion = 2;
				tecla = 13;
				break;
            case 'c': 
				opcion = 3;
				tecla = 13;
				break;				
            case 'm': 
				opcion = 4;
				tecla = 13;
				break;
            case 'o': 
				opcion = 5;
				tecla = 13;
				break;
            case 'g': 
				opcion = 6;
				tecla = 13;
				break;
            case 'r': 
				opcion = 7;
				tecla = 13;
				break;
            case 's': 
				opcion = 8;
				tecla = 13;
				break;														
        }
	}while(tecla != 13);
	system("cls");
	return opcion;
}
int respuesta(){
	int opcion = 1;
    int tecla;
    do{
    	gotoxy(53,13);printf("SI[   ]  NO[   ]");
		gotoxy(45,15);printf("Presione [ENTER] para continuar");
		gotoxy(48+(opcion*9),13); printf("");
		tecla = getch();
		switch (tecla) {
            case 75:  
                if (opcion > 1) opcion--;
                break;
            case 77: 
                if (opcion < 2) opcion++;
                break;
        }
	}while(tecla != 13);
	return opcion;
}

void gotoxy(int x,int y){  
    HANDLE hcon;  
    hcon = GetStdHandle(STD_OUTPUT_HANDLE);  
    COORD dwPos;  
    dwPos.X = x;  
    dwPos.Y= y;  
    SetConsoleCursorPosition(hcon,dwPos);  
} 
