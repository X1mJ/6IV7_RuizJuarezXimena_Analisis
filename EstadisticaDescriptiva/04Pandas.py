import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('./EstadisticaDescriptiva/housing.csv')

#mostrar las primeras 5 filas
print(df.head())

#las ultimas 5 filas
print(df.tail())

#una fila en espeficico
print(df.iloc[7])

#obtener la mediana de la columna population
medianapopularidad = df["population"].median()
print('Mediana popularidad: ', medianapopularidad)

std_age = df["housing_median_age"].std()
print('desviacion Estandar en años: ', std_age)

#para filtrar
filtrodeloceano = df[df["ocean_proximity"] == "ISLAND"]
print('filtro de proximidad del oceano : ', filtrodeloceano)

#vamos a crear un grafico de dispersion entre los registros de la proximidad del oceano vs los precios
plt.scatter(df["ocean_proximity"][:10], df["housing_median_age"][:10])

#hay que definir a x y
plt.xlabel('Proximidad')
plt.ylabel('Precio')

plt.title('Grafico de Dispersion de Proximidad al oceano vs precio')
plt.show()