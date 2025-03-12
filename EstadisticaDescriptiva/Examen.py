import pandas as pd
import matplotlib.pyplot as plt

#Obtener Media, mediana, moda, rango, varianza, desviasion estandar con su tabla de frecuencias del archivo housing, 
#deberan de mostrarse en un formato de tablas separado con sus elementos solicitados.

df = pd.read_csv('./EstadisticaDescriptiva/housing.csv')

def estadisticas_datos(datos):
    datos = pd.Series(datos)
    estadistica = pd.Series([datos.min(), datos.max(), datos.mean(), datos.std()], index =['Min', 'Max', 'Media', 'Desviacion Estandar'])
    return estadistica


#obtener la mediana de la columna population
mdValue = df["median_house_value"].median()
print('Medianas: ', mdValue)

std_age = df["housing_median_age"].std()
print('desviacion Estandar en años: ', std_age)




#vamos a crear un grafico de dispersion entre los registros de la proximidad del oceano vs los precios
plt.scatter(df["ocean_proximity"][:10], df["housing_median_age"][:10])

#hay que definir a x y
plt.xlabel('Proximidad')
plt.ylabel('Precio')

plt.title('Grafico de Dispersion de Proximidad al oceano vs precio')
plt.show()