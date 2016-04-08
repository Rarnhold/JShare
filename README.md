# JShare117060
Critério de avaliação: sistema funcionando = 1,5 ponto. Não há nota parcial visto que há tempo o suficiente para terminar o trabalho. O aluno que não fizer o trabalho não terá condições de fazer a prova bimestral, já que praticamente metade do conteúdo da prova é referente ao conceito utilizado, às classes e métodos desse trabalho.

# Descrição do sistema a ser feito.
O nome do projeto deve ser JShareXXX onde XXX é o teu RA sem pontos.

O JShare é um compartilhador de arquivos. 

É uma aplicação RMI que se conecta em outra instância da mesma aplicação para troca de arquivos. Ela possui uma pasta no HD onde disponibiliza os seus arquivos e outra pasta onde baixa os arquivos de outros computadores. Ex: C:\JShare\Uploads e C:\JShare\Downloads.

Tanto cliente como servidor (concentrador) implementam a mesma interface. O que os diferencia é a eleição arbitrária de um servidor, cujo IP é publicado para que todos os outros conectem.

Cada instância se exporta como servidor e também se registra no concentrador. 

Todos os integrantes da rede devem publicar seus arquivos no concentrador. 

As buscas por arquivos devem ser feitas no concentrador. 

No entanto, quando um cliente quiser baixar um arquivo, ele solicita diretamente ao cliente que possui o arquivo, e não para o concentrador que possui somente os nomes.

Crie uma tela em Swing ou JavaFX, o que preferir, para digitar os parâmetros de conexão.

Critério de pronto: sistema funcionando e conectando com os micros dos colegas.