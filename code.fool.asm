push 0
push 3
push 5
push 2
push classplusfool
new
lfp
push 4
push -2
lfp
add
lw
push -2
lfp
add
lw
lw
push 4
add
lam
js
print
pop
halt

function0:
cfp
lra
lfp
push 0
lfp
add
lw
push 0
lfp
add
lw
lw
push 1
add
lam
js
lfp
push 0
lfp
add
lw
push 0
lfp
add
lw
lw
push 2
add
lam
js
add
srv
sra
pop
sfp
lrv
lra
js

function1:
cfp
lra
push 1
lfp
lw
add
lw
srv
sra
pop
sfp
lrv
lra
js

function2:
cfp
lra
push 2
lfp
lw
add
lw
srv
sra
pop
sfp
lrv
lra
js

function3:
cfp
lra
push 1
lfp
add
lw
push 2
lfp
add
lw
mult
srv
sra
pop
pop
pop
sfp
lrv
lra
js

function4:
cfp
lra
push 1
lfp
add
lw
push 1
beq label2
push 0
b label3
label2:
push 1
label3:
push 1
beq label0
lfp
lfp
push 1
lfp
add
lw
push 1
sub
push 0
lfp
add
lw
push 0
lfp
add
lw
lw
push 4
add
lam
js
push 1
lfp
add
lw
push 0
lfp
add
lw
push 0
lfp
add
lw
lw
push 3
add
lam
js
b label1
label0 :
push 1
label1 :
srv
sra
pop
pop
sfp
lrv
lra
js
classplusfool:
function0
function1
function2
function3
function4
classfoo:
function0
function1
function2
function3
