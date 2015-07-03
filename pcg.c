#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#define MAX_LINE_SIZE   256
#define MAX_WORD_INLINE 20

/* function taken from 
 * http://www.java2s.com/Code/C/String/Findsubstringourownfunction.htm
 * Return index of first match of itemPointer in listPointer */
int find_substr(char *listPointer, char *itemPointer)
{
  int t, i;
  char *p, *p2;

  for(t=0; listPointer[t]; t++) {
    p = &listPointer[t];
    p2 = itemPointer;

    while(*p2 && *p2==*p) {
      p++;
      p2++;
    }
    if(!*p2) return t; /* 1st return */
  }
   return -1; /* 2nd return */
}

/*
 *  print string from str[from] to str[to]
 */
void print_substr(char *str, int from, int to)
{
    for( ; from < to && ((int)str[from]) >= (int)' ' && ((int)str[from]) <= (int)'~'; from++ )
    {
            printf("%c", str[from]);
    }
}

/*
 *	return first position of '\0' in given string
 */
int get_end_str(char *str)
{
	int i;
	for (i = 0 ; i < MAX_LINE_SIZE ; i++)
		if( str[i] == '\0')
			return i;
	return -1;
}

/*
 * return position of nearest space (' ') in given string
 */
 int get_space_pos(char *str, int from/*, int to*/)
 {
 	/*if( to == 0)
 		to = MAX_LINE_SIZE;*/
 	for(  ; from < MAX_LINE_SIZE ; from++)
 	{
 		if(str[from] == ' ')
 			return from;
 	}
 	return -1;
 }

int main(int argc, char** argv) 
{
    static char *ctopl[34][2] = {
        //chopy, wołajta polonistę, bo nie ogarniam
        {"auto",        "automatyczny"},
        {"break",       "przerwij"},
        {"case",        "przypadek"},
        {"char",        "znak"},
        {"const",       "stały"},
        {"continue",    "kontynuuj"},
        {"default",     "domyślny"},
        {"do",          "wykonuj"},
        {"double",      "podwójny"},    //podwojna dokładność po przecinku
        {"else",        "albo"},        //w przeciwnym przypadku
        {"enum",        "lista"},       //lista wyliczeniowa
        {"extern",      "deklaracja"},  
        {"float",       "pływający"},   //pojedyńcza dokładność po przecinku
        {"for",         "dla"}, 
        {"goto"         "idźdo"},   
        {"if",          "jeśli"},
        {"int",         "całkowity"},
        {"include",     "dołącz"},
        {"long",        "długi"},
        {"register",    "zarejestrowany"},
        {"return",      "oddanie"},		//{"zwróć"}
        {"short",       "krótki"},      //zakres l. całkowitej od -32768 do 32767
        {"signed",      "zeznakiem"},   //{"podpisana"?} 
        {"sizeof",      "rozmiar"},
        {"static",      "statyczny"},
        {"struct",      "struktura"},
        {"switch",      "przełącznik"},
        {"typedef",     "typwyliczeniowy"}, //{"wyliczeniówka"?}
        {"union",       "unia"},
        {"unsigned"     "bezznakowy"},  
        {"void",        "pusty"},
        {"volatile",    "lotny"},
        {"while",       "dopóki"},
        {"main",        "główna"}
    };
    int i = 0;
    int z = 0;
    int w = 0;
    int j = 0;
    int flaga = 0;
    int counter = 0;
    int *v = malloc(1 * sizeof *v); //v[1];
    int *v2;

    char line[MAX_LINE_SIZE];
    char words[24][MAX_WORD_INLINE]; //we do not need more, thought

    FILE * f = fopen("C:\\users\\re\\pcg.txt", "w");
    if( f == NULL ){printf(":(");exit(0);}

 	setlocale(LC_ALL, "pl-PL");

    do{
        fgets(line, MAX_LINE_SIZE, stdin);

        for (i = 0; i < 34 ; ++i)
        {   //search how many words in line we have
            if( (z = find_substr(line, ctopl[i][0])) != -1)
            {
                counter++;
                for( ; z < MAX_LINE_SIZE 
                    && ((int)str[z]) >= (int)' ' 
                    && ((int)str[z]) <= (int)'~'
                    ; from++ )
                    words[counter] = print_substr
                    printf("%c", str[from]);
                
                v2 = realloc(v, (1+flaga) * sizeof *v2);
            }

        }

        printf("%d", counter);
        for( ; counter > 0; counter--)
        {
            v2[counter - 1] = 9;
            printf("%d ", v2[counter-1]);
        }
        counter = 0;
        //exit(0);        
        
        for (i = 0; i < 34 ; ++i)
        {	
        	if( (z = find_substr(line, ctopl[i][0])) != -1)
        	{	
        		//for %c to first whitespace 
        		//printf("%d: %s->%s ", z, ctopl[i][0], ctopl[i][1]);
        		w = get_space_pos(line, z);
        		printf("w:%d\t", w);

        		if( z == 0 ) //start of english word is at the beginning of line 
        		{
        			flaga = 1;
        			puts(";");
        			//then print polish version
        			fprintf(f, "%s", ctopl[i][1]);        		
        			//and print rest of line
        			//print_substr(line, w, MAX_LINE_SIZE);
        			for( ; w < MAX_LINE_SIZE && ((int)line[w]) >= (int)' ' && ((int)line[w]) <= (int)'~'; w++ )
            			fprintf(f, "%c", line[w]);

        		}else 
        		if( z > 0 ) //  start of english word is somewhere further 
     	   		{
     	   			flaga = 1;
     	   			puts(":");
        			//print_substr(line, 0, z);
        			//print text before that found english word 
        			j = 0;
        			for( ; j < z && ((int)line[j]) >= (int)' ' && ((int)line[j]) <= (int)'~'; j++ )
            			fprintf(f, "%c", line[j]);
        			//print polish version
        			fprintf(f, "%s", ctopl[i][1]);
        			//and print rest of line
        			for( ; w < MAX_LINE_SIZE && ((int)line[w]) >= (int)' ' && ((int)line[w]) <= (int)'~'; w++ )
            			fprintf(f, "%c", line[w]);
        		}
				//i assume that there is ONLY one english special word in line...
				//
        	}/*else
            if( z == -1 && flaga == 0) // english word not found, so print what we have
        	{
        		for (j = 0; j < MAX_LINE_SIZE && ((int)line[j]) >= (int)' ' && ((int)line[j]) <= (int)'~'; ++j)
        			fprintf(f, "|%c", line[j]);
        		break;
        		flaga = 0;
        	}*/
        	fprintf(f, "\n");
            break;
		}
    }while(!feof(stdin) && line[0] != '!');
    close(f);
    return (EXIT_SUCCESS);
}