# Flake Nap

## Nap lifecycle

```mermaid
graph LR

init[Initial]
work[Working]
weak[Weaking]
wait[Waiting just]

init -- start --> work
work -- interrupt for other work --> work
work -- interrupt for weak --> weak
work -- finish --> wait
wait -- weak --> weak
weak -- finish --> wait
wait -- start work --> work 
```

## TODO

- [ ] Detect continuing work times
