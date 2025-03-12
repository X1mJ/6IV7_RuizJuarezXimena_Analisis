import pandas as pd

#vamos hacer un ejemplo de carga de archivo  y aplimar min, max, media y desviacion estandar

def cotizacion(fichero):
    df = pd.read_csv(fichero, sep=';', decimal =',', thousands='.', index_col=0)
    return pd.DataFrame([df.min(), df.max(), df.mean(), df.std()], index=['Minimo', 'Maximo', 'Media', 'Desviacion Estandar'])

print(cotizacion('./EstadisticaDescriptiva/cotizacion.csv'))