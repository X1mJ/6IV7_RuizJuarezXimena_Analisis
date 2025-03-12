#Obtener Media, mediana, moda, rango, varianza, desviasion estandar con su tabla de frecuencias del archivo housing, deberan de mostrarse en un formato de tablas separado con sus elementos solicitados.
#Grafico de barras de median_house_value, comparandolo con population y median_house_value  respecto del costo de promedio de median_house_value

import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('./EstadisticaDescriptiva/housing.csv')

datos = df['median_house_value']

# Tablita que calcula la media, md, mo, rango, varianza y desviacion estandar
resumen = pd.DataFrame({
    'Media': [datos.mean()], 'Mediana': [datos.median()], 'Moda': [datos.mode()[0]],
    'Rango': [datos.max() - datos.min()], 'Varianza': [datos.var()], 'DesviacionEstandar': [datos.std()]
})
print(resumen)

# Tabla de frecuencias
print(datos.value_counts().head(10))

# Grafica de barras comparando median_house_value y population
plt.bar(df.index[:20], df['median_house_value'][:20], label='Valor Medio', color='b')
plt.bar(df.index[:20], df['population'][:20], label='Población', color='r', alpha=0.5)
plt.axhline(datos.mean(), color='g', linestyle='dashed', linewidth=2, label='Promedio')
plt.xlabel('Índice')
plt.ylabel('Valores')
plt.title('Comparación de Valores')
plt.legend()
plt.show()