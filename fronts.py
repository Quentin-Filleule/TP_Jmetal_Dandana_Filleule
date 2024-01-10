import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.patches import Circle

# Pour TP1
data = pd.read_csv('FUN', delimiter=" ", names=['col1', 'col2', 'nan'])
data.sort_values(by=['col1'], ascending=False, inplace=True)
plt.plot(data['col1'], data['col2'])
plt.title("Front de pareto TP1")
plt.xlabel('F1')
plt.ylabel('F2')
plt.show()



# Pour TP2
# Lire les données depuis un fichier CSV
df = pd.read_csv('FUN_capteurs', sep=' ', header=None, names=['X', 'Y', 'nan'])

# Tri des données pour faciliter la visualisation du front de Pareto
df_sorted = df.sort_values(by='X')

# Tracer les points
plt.scatter(-df_sorted['X'], -df_sorted['Y'])

# Ajout de titres et labels (optionnel)
plt.title('Front de Pareto TP2')
plt.xlabel('F1')
plt.ylabel('F2')

# Afficher le graphique
plt.show()


# Affichage des cibles avec les capteurs

cibles = [
    [50, 50],
    [41, 50],
    [90, 90],
    [86, 89],
    [10, 10],
    [15, 95]
]

# Exemple capteurs
capteurs = [
    [39.1248998721233, 59.80997877563867],
    [17.40314723633268, 13.683716838770671],
    [44.56087810135955, 47.22411692728908],
    [39.737056894151266, 52.47784484465061],
    [23.400479681679062, 94.33857662560717],
    [79.72464872360469, 87.45184462022047],
    [81.5977774161915, 92.33761055598251],
    [94.78841514662737, 93.01848470027815],
    [13.554059044007216, 88.25127286013189],
    [3.9113986758849877, 5.728330169090415]
]


# Séparer les coordonnées x et y
xCible = [point[0] for point in cibles]
yCible = [point[1] for point in cibles]

xCapteur = [point[0] for point in capteurs]
yCapteur = [point[1] for point in capteurs]

# Créer un graphique avec des carrés
plt.plot(xCible, yCible, 'rs', markersize=10, label='Cibles')
plt.plot(xCapteur, yCapteur, 'bo', markersize=7, label='Capteurs')

for i in range(len(xCapteur)):
    cercle = Circle((xCapteur[i], yCapteur[i]), radius=10, color='green', alpha=0.3)
    plt.gca().add_patch(cercle)

# Ajouter des titres et des labels
plt.title('Cibles et Capteurs')
plt.xlabel('Axe X')
plt.ylabel('Axe Y')

plt.legend()

# Afficher le graphique
plt.show()