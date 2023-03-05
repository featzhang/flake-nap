# Flake Nap

## Nap lifecycle

```mermaid
graph LR

init[Initial]
work[Working]
rest[Resting]
wait[Waiting just]

init -- start --> work
work -- interrupt for other work --> work
work -- interrupt for rest --> rest
work -- finish --> wait
wait -- rest --> rest
rest -- finish --> wait
wait -- start work --> work 
```

## TODO

- [ ] Detect continuing work times
