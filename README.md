# Detecção de Pessoas em Imagens através do uso de Inteligência Computacional

Este é um programa de **Detecção de Pessoas em Imagens**. O projeto tem como entrada uma imagem, 
e como saída, a informação da existência ou não de pessoas contidas na mesma. A partir daí, utiliza-se essa 
informação para definir algumas simulações (e.g. ligar/desligar aparelho de ar condicionado, ligar/desligar televisões ou ligar/desligar uma lampada).

## Execução

O programa roda a partir da IDE Eclipse. Ao abrir o projeto por ela, basta selecionar a classe **DetectorDePessoas** no pacote br.ufrn.imd e roda-la. Para funcionamento do projeto é necessario o uso das bibliotecas **OpenCV**, **JavaFX** e **WebCam-sarxos** (Tutorial de instalação no tópico **Biblioteca adicional**). O programa consiste em 5 telas, sendo elas: 

* Simulation: nela podemos escolher as métricas que serão utilizadas na detecção e o valor de K.
* Simulation Settings: tela responsavel pela configuração da simulação (e.g. ligar/desligar aparelho de ar condicionado, ligar/desligar televisões ou ligar/desligar uma lampada).
* Input Settings: tela onde é definido o arquivo que vai ser analisado, podendo ele ser selecionado de uma pasta do sistema ou uma foto tirada pela webcam.
* WebCam preview: nela podemos visualizar nossa webcam e tirar uma foto.
* About: tela contendo algumas informações sobre os desenvolvedores.

### Biblioteca Adicional

Segue abaixo o link para baixar a biblioteca em .zip. Esse arquivo ZIP possui a Webcam Capture API e todas as suas dependencias (na pasta libs).

 [webcam-capture-0.3.12-dist.zip](https://github.com/sarxos/webcam-capture/releases/download/webcam-capture-parent-0.3.12/webcam-capture-0.3.12-dist.zip)

## FixMe

Ao acessar a tela da WebCam, uma exceção é lançada. Apesar disso, o programa funciona normalmente.

**Developed by Brunno Monte (bmonte@outlook.com) and Nathan Andre (nathan_99_@hotmail.com).
